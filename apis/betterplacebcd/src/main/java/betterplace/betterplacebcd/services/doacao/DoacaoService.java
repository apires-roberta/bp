package betterplace.betterplacebcd.services.doacao;

import betterplace.betterplacebcd.classes.ed.Notificacao;
import betterplace.betterplacebcd.data.dto.doacao.CreateDoacaoDto;
import betterplace.betterplacebcd.data.dto.doacao.ReadDoacaoDto;
import betterplace.betterplacebcd.data.dto.doador.UpdateDoadorDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.servicesreferences.IDoadorService;
import betterplace.betterplacebcd.servicesreferences.IOngService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoacaoService implements IDoacaoService {
    @Autowired
    private CampanhaRepository _campanhaRepository;
    @Autowired
    private DoacoesRepository _doacoesRepository;
    @Autowired
    private IDoadorService _doadorService;
    @Autowired
    private IOngService _ongService;
    @Autowired
    private ModelMapper _mapper;

    @Override
    public Integer doar(CreateDoacaoDto doacaoDto) {
        Campanha campanha = _campanhaRepository.findByIdCampanha(doacaoDto.getIdCampanha());
        Doador doador = _mapper.map(_doadorService.getUsuarioById(doacaoDto.getIdDoador()), Doador.class);

        Doacao doacao = new Doacao(campanha, doador, doacaoDto.getValorDoacao());
        _doacoesRepository.save(doacao);

        UpdateDoadorDto updateDoador = new UpdateDoadorDto();
        updateDoador.setPontuacao(doador.getPontuacao() + (5 + doacao.getValorDoacao() * 0.5));
        _doadorService.atualizarDadosCadastrais(doador.getCod(), updateDoador);

        //notificar(doacao); //Tempo de Execução: 6261061200ns
        Thread thread = new Thread(() -> {
            notificar(doacao);
        }); //Tempo de Execução: 248600 ns

        thread.start();

        return doacao.getIdDoacao();
    }

    @Override
    public List<ReadDoacaoDto> getDoacoesByIdCampanha(Integer idCampanha) {
        List<Doacao> doacoes = _doacoesRepository.findByCampanhaIdCampanhaOrderByDataDoacaoDesc(idCampanha);
        List<ReadDoacaoDto> doacoesDto = new ArrayList<>();

        if (doacoes.isEmpty())
            return doacoesDto;

        for (Doacao doacao : doacoes) {
            doacoesDto.add(_mapper.map(doacao, ReadDoacaoDto.class));
        }

        return doacoesDto;
    }

    @Override
    public ReadDoacaoDto getDoacaoByIdDoacao(Integer idDoacao) {
            Doacao doacao = _doacoesRepository.findByIdDoacao(idDoacao);
            return doacao != null ? _mapper.map(doacao, ReadDoacaoDto.class) : null;
    }

    @Override
    public ReadDoacaoDto getUltimaDoacaoDoadorCampanha(Integer idDoador, Integer idCampanha) {
        Doacao doacao = _doacoesRepository.findFirstByDoadorCodAndCampanhaIdCampanhaOrderByDataDoacaoDesc(idDoador, idCampanha);
        return doacao != null ? _mapper.map(doacao, ReadDoacaoDto.class) : null;
    }

    @Override
    public Double getTotalRecebidoOng(Integer idOng) {
        verificaOngExiste(idOng);

        Double valor = _doacoesRepository.sumValorDoadoOng(idOng);

        return valor == null ? 0 : valor;
    }

    private void notificar(Doacao doacao) {
        String mensagem = String.format("%s mandou R$%.2f para campanha: %s", doacao.getDoador().getNome(),
                doacao.getValorDoacao(), doacao.getCampanha().getNomeCampanha());
        String email = doacao.getCampanha().getOng().getEmail();
        Notificacao novaNotificacao = new Notificacao();
        novaNotificacao.novaDoacao(email, mensagem);
    }

    private void verificaOngExiste(Integer idOng) {
        _ongService.getOngById(idOng);
    }
}