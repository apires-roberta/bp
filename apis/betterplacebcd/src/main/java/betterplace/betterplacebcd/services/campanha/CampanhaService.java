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
        List<Campanha> campanhas = _campanhaRepository.findByDisponivelTrueOrderByRecomendadoDataCriacaoDesc();
        if (campanhas.isEmpty())
            return null;

        return getReadCampanhaDtoComTotalDoado(campanhas);
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

        if (_doacoesRepository.sumValorDoadoCampanha(cod) > 0)
            throw new IllegalStateException("Campanha já contém doações");
        _campanhaRepository.delete(campanha);
    }

    @Override
    public List<ReadCampanhaDto> getCampanhaByFkOng(Integer fkOng) {
        List<Campanha> campanhas = _campanhaRepository.findByOngCodOrderByDataCriacaoDesc(fkOng);
        if (campanhas == null || campanhas.isEmpty())
            return null;

        return getReadCampanhaDtoComTotalDoado(campanhas);
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
        List<ReadCampanhaDto> campanhas = getReadCampanhaDtoComTotalDoado(_campanhaRepository.findByDisponivelTrueAndIdCampanhaIsNot(idCampanha));
        List<ReadCampanhaDto> campanhasRecomendadas = new ArrayList<>();

        for (ReadCampanhaDto campanha : campanhas) {
            hashTable.insere(campanha.getIdCampanha(), campanha.getTipoCampanha().getIdTipoCampanha());
        }

        Integer tipoCampanha = _campanhaRepository.findTipoCampanhaByIdCampanha(idCampanha);
        tipoCampanha = tipoCampanha + 1; //Tive que colocar esse +1 por causa de um bug bizarro do jpa que retorna o tipo_campanha - 1, mesmo a query dando certo no mysql

        Node recomendacao = hashTable.busca(tipoCampanha);
        for (int i = 0; i < 3 && recomendacao != null; i++) {
            campanhasRecomendadas.add(getReadCampanhaDtoComTotalDoado(_campanhaRepository.findByIdCampanha(recomendacao.getInfo())));
            recomendacao = recomendacao.getNext();
        }
        return campanhasRecomendadas;
    }

    @Override
    public List<ReadCampanhaDto> getCampanhasDisponiveisByOng(Integer idOng) {
        verificaOngExiste(idOng);
        List<Campanha> campanhasDisponiveis = _campanhaRepository.findByOngCodAndDisponivelTrueOrderByDataCriacaoDesc(idOng);

        return getReadCampanhaDtoComTotalDoado(campanhasDisponiveis);
    }

    @Override
    public void indisponibilizarTodasCampanhasByOng(Integer idOng) {
        verificaOngExiste(idOng);
        List<Campanha> campanhas = _campanhaRepository.findByOngCodOrderByDataCriacaoDesc(idOng);

        for (Campanha campanha : campanhas) {
            campanha.setDisponivel(false);
            _campanhaRepository.save(campanha);
        }
    }

    @Override
    public Integer getQuantidadeCampanhasDisponiveisByOng(Integer idOng) {
        verificaOngExiste(idOng);
        Integer qtdCampanhasDisponiveis = _campanhaRepository.countByOngCodAndDisponivelTrue(idOng);

        return qtdCampanhasDisponiveis == null ? 0 : qtdCampanhasDisponiveis;
    }

    @Override
    public Integer getQuantidadeCampanhasTotalByOng(Integer idOng) {
        verificaOngExiste(idOng);
        Integer qtdCampanhasTotal = _campanhaRepository.countByOngCod(idOng);

        return qtdCampanhasTotal == null ? 0 : qtdCampanhasTotal;
    }

    @Override
    public List<ReadCampanhaDto> getRecomendacoesPorDoacoesByIdCampanha(int idCampanha, int idDoador) {
        return getReadCampanhaDtoComTotalDoado(_campanhaRepository.getRecomendacoesByDoacoes(idCampanha, idDoador));
    }

    private void verificaOngExiste(Integer idOng) {
        _ongService.getOngById(idOng);
    }

    private List<ReadCampanhaDto> getReadCampanhaDtoComTotalDoado(List<Campanha> campanhas) {
        List<ReadCampanhaDto> readCampanhaDtos = new ArrayList<>();

        for (Campanha campanha : campanhas) {
            ReadCampanhaDto campanhaDto = _mapper.map(campanha, ReadCampanhaDto.class);
            campanhaDto.setTotalDoado(_doacoesRepository.sumValorDoadoCampanha(campanha.getIdCampanha()));
            readCampanhaDtos.add(campanhaDto);
        }
        return readCampanhaDtos;
    }

    private ReadCampanhaDto getReadCampanhaDtoComTotalDoado(Campanha campanha){
        ReadCampanhaDto dto = _mapper.map(campanha, ReadCampanhaDto.class);
        dto.setTotalDoado(_doacoesRepository.sumValorDoadoCampanha(campanha.getIdCampanha()));

        return dto;
    }
}