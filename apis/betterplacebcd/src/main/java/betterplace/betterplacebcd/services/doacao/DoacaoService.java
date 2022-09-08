package betterplace.betterplacebcd.services.doacao;

import betterplace.betterplacebcd.classes.Notificacao;
import betterplace.betterplacebcd.data.dto.doacao.CreateDoacaoDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.servicesreferences.IDoadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoacaoService implements IDoacaoService{
    @Autowired
    private CampanhaRepository campanhaRepository;
    @Autowired
    private DoacoesRepository doacoesRepository;
    @Autowired
    private IDoadorService _doadorService;
    @Autowired
    private ModelMapper _mapper;
    @Override
    public void doar(CreateDoacaoDto doacaoDto) {
        Campanha campanha = campanhaRepository.findByIdCampanha(doacaoDto.getIdCampanha());
        Doador doador = _mapper.map(_doadorService.getUsuarioById(doacaoDto.getIdDoador()), Doador.class);

        Doacao doacao = new Doacao(campanha, doador, doacaoDto.getValorDoacao());
        doacoesRepository.save(doacao);
        notificar(doacao);
    }

    private void notificar(Doacao doacao) {
        String mensagem = String.format("%s mandou R$%.2f para campanha: %s", doacao.getDoador().getNome(),
                doacao.getValorDoacao(), doacao.getCampanha().getNomeCampanha());
        String email = doacao.getCampanha().getOng().getEmail();
        Notificacao novaNotificacao = new Notificacao();
        novaNotificacao.novaDoacao(email, mensagem);
    }
}