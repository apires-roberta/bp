package betterplace.betterplacebcd.services.campanha;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.servicesreferences.IOngService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@Service
public class CampanhaService implements ICampanhaService{
    @Autowired
    private CampanhaRepository repository;
    @Autowired
    private IOngService _ongService;
    @Autowired
    private ModelMapper _mapper;
    @Override
    public void postCampanha(CreateCampanhaDto novaCampanha) {
        ReadUsuarioDto ong = _ongService.getOngById(novaCampanha.getFkOng());
        Campanha campanha = new Campanha(novaCampanha, _mapper.map(ong, Ong.class));
        repository.save(campanha);
    }

    @Override
    public List<ReadCampanhaDto> getAllCampanhas() {
        List<Campanha> campanhas = repository.findAll();
        if (campanhas.isEmpty()) {
            return null;
        }

        List<ReadCampanhaDto> campanhasDto = campanhas.stream()
                                                      .map(campanha -> _mapper.map(campanha, ReadCampanhaDto.class))
                                                      .collect(Collectors.toList());
        return campanhasDto;
    }

    @Override
    public void alterarValor(Integer cod, Double valorNovo) {
        Campanha campanha = repository.findByIdCampanha(cod);

        if (campanha == null)
            throw new IllegalArgumentException(String.format("Campanha de ID %d não existe!", cod));

        campanha.setValorNecessario(valorNovo);
        repository.save(campanha);
    }

    @Override
    public void apagarCampanha(Integer cod) {
        Campanha campanha = repository.findByIdCampanha(cod);
        if (campanha == null)
            throw new IllegalArgumentException(String.format("Campanha de ID %d não existe!", cod));

        repository.delete(campanha);
    }
}
