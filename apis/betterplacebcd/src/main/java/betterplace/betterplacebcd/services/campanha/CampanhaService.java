package betterplace.betterplacebcd.services.campanha;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
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

 /*       List<ReadCampanhaDto> readCampanhaDtos = campanhas.stream()
                                                      .map(campanha -> _mapper.map(campanha, ReadCampanhaDto.class))
                                                      .collect(Collectors.toList());*/
        List<ReadCampanhaDto> readCampanhaDtos = new ArrayList<>();

        for (Campanha campanha : campanhas) {
            ReadCampanhaDto campanhaDto = _mapper.map(campanha, ReadCampanhaDto.class);
            campanhaDto.setTotalDoado(_doacoesRepository.sumValorDoadoCampanha(campanha.getIdCampanha()));
            readCampanhaDtos.add(campanhaDto);
        }
        return readCampanhaDtos;
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

        List<ReadCampanhaDto> readCampanhaDtos = new ArrayList<>();
        for (Campanha campanha : campanhas) {
            ReadCampanhaDto campanhaDto = _mapper.map(campanha, ReadCampanhaDto.class);
            campanhaDto.setTotalDoado(_doacoesRepository.sumValorDoadoCampanha(campanha.getIdCampanha()));
            readCampanhaDtos.add(campanhaDto);
        }

        return readCampanhaDtos;
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
}