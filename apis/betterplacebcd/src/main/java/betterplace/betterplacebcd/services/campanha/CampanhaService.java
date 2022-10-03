package betterplace.betterplacebcd.services.campanha;

import betterplace.betterplacebcd.classes.ed.HashTable;
import betterplace.betterplacebcd.classes.ed.Node;
import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
import betterplace.betterplacebcd.data.enums.TipoCampanha;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.servicesreferences.IOngService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampanhaService implements ICampanhaService{
    @Autowired
    private CampanhaRepository _campanhaRepository;
    @Autowired
    DoacoesRepository _doacoesRepository;
    @Autowired
    private IOngService _ongService;
    @Autowired
    private ModelMapper _mapper;
    @Override
    public void postCampanha(CreateCampanhaDto novaCampanha) {
        ReadUsuarioDto ong = _ongService.getOngById(novaCampanha.getFkOng());
        Campanha campanha = new Campanha(novaCampanha, _mapper.map(ong, Ong.class));
        _campanhaRepository.save(campanha);
    }

    @Override
    public List<ReadCampanhaDto> getAllCampanhas() {
        List<Campanha> campanhas = _campanhaRepository.findAll();
        if (campanhas.isEmpty())
            return null;

        return getReadCampanhaDtosComTotalDoado(campanhas);
    }

    @Override
    public void alterarValor(Integer cod, Double valorNovo) {
        Campanha campanha = _campanhaRepository.findByIdCampanha(cod);
        if (campanha == null)
            throw new IllegalArgumentException(String.format("Campanha de ID %d não existe!", cod));

        campanha.setValorNecessario(valorNovo);
        _campanhaRepository.save(campanha);
    }

    @Override
    public void apagarCampanha(Integer cod) {
        Campanha campanha = _campanhaRepository.findByIdCampanha(cod);
        if (campanha == null)
            throw new IllegalArgumentException(String.format("Campanha de ID %d não existe!", cod));

        _campanhaRepository.delete(campanha);
    }

    @Override
    public List<ReadCampanhaDto> getCampanhaByFkOng(Integer fkOng) {
        List<Campanha> campanhas = _campanhaRepository.findByOngCod(fkOng);
        if (campanhas == null || campanhas.isEmpty())
            return null;

        return getReadCampanhaDtosComTotalDoado(campanhas);
    }

    @Override
    public ReadCampanhaDto getCampanhaById(Integer idCampanha) {
        Campanha campanha = _campanhaRepository.findByIdCampanha(idCampanha);
        if (campanha == null) return null;

        ReadCampanhaDto campanhaDto = _mapper.map(campanha, ReadCampanhaDto.class);
        campanhaDto.setTotalDoado(_doacoesRepository.sumValorDoadoCampanha(campanha.getIdCampanha()));

        return campanhaDto;
    }

    @Override
    public void alterarDisponibilidadeCampanha(Integer idCampanha) {
        Campanha campanha = _campanhaRepository.findByIdCampanha(idCampanha);
        if (campanha == null)
            throw new NullPointerException();

        campanha.setDisponivel(!campanha.isDisponivel());
        _campanhaRepository.save(campanha);
    }

    @Override
    public List<ReadCampanhaDto> getRecomendacoesByIdCampanha(int idCampanha) {
        HashTable hashTable = new HashTable(TipoCampanha.values().length);
        List<Campanha> campanhas = _campanhaRepository.findByDisponivelTrue();

        //campanhas.stream().forEach(campanha -> hashTable.insere(campanha.getTipoCampanha().getIdTipoCampanha()));
//        for (Campanha campanha : campanhas) {
//            hashTable.insere(campanha.getTipoCampanha().getIdTipoCampanha());
//        }
        //TODO Colocar HashTable do tipo Campanha
        Node nodeBuscado = hashTable.busca(idCampanha);

        if (nodeBuscado == null)
            throw new NullPointerException();

        List<ReadCampanhaDto> campanhasRecomendadas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            if (nodeBuscado == null)
                break;

            campanhasRecomendadas.add(_mapper.map(_campanhaRepository.findByIdCampanha(nodeBuscado.getInfo()), ReadCampanhaDto.class));
            nodeBuscado = nodeBuscado.getNext();
        }

        return campanhasRecomendadas;
    }

    private List<ReadCampanhaDto> getReadCampanhaDtosComTotalDoado(List<Campanha> campanhas) {
        List<ReadCampanhaDto> readCampanhaDtos = new ArrayList<>();

        for (Campanha campanha : campanhas) {
            ReadCampanhaDto campanhaDto = _mapper.map(campanha, ReadCampanhaDto.class);
            campanhaDto.setTotalDoado(_doacoesRepository.sumValorDoadoCampanha(campanha.getIdCampanha()));
            readCampanhaDtos.add(campanhaDto);
        }
        return readCampanhaDtos;
    }
}