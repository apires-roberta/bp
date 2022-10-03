package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.services.campanha.ICampanhaService;
import betterplace.betterplacebcd.servicesreferences.IOngService;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@Service
@RestController
@CrossOrigin
@RequestMapping("/campanha")
public class CampanhaController {
    @Autowired
    private ICampanhaService _campanhaService;

    @PostMapping
    public ResponseEntity<?> postCampanha(@RequestBody @Valid CreateCampanhaDto novaCampanha) {
        if (novaCampanha.getNomeCampanha() == null || novaCampanha.getDescCampanha() == null || novaCampanha.getValorNecessario() == null)
            return ResponseEntity.status(400).build();
        try {
            _campanhaService.postCampanha(novaCampanha);
            return status(201).build();
        } catch (FeignException.NotFound ex) {
            return status(404).build();
        } catch (FeignException.ServiceUnavailable ex) {
            return status(503).build();
        } catch (IllegalArgumentException ex) {
            return status(400).body("Tipo de Campanha '"+ novaCampanha.getTipoCampanha() +"' não existe");
        } catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/Ong/{fkOng}")
    public ResponseEntity<List<ReadCampanhaDto>> getCampanhasByFkOng(@PathVariable Integer fkOng) {
        if (fkOng == null || fkOng == 0)
            return status(400).build();

        List<ReadCampanhaDto> campanhas = _campanhaService.getCampanhaByFkOng(fkOng);

        if (campanhas == null || campanhas.isEmpty())
            return status(204).build();

        return status(200).body(campanhas);
    }

    @GetMapping
    public ResponseEntity<?> getAllCampanhas() {
        List<ReadCampanhaDto> campanhas = _campanhaService.getAllCampanhas();
        if (campanhas == null)
            return status(204).build();

        return status(200).body(campanhas);
    }

    @PatchMapping("/alterarValor/{cod}/{valorNovo}")
    public ResponseEntity<?> alterarValor(@PathVariable Integer cod, @PathVariable Double valorNovo) {
        if (cod == null || cod <= 0)
            return status(400).build();

        try {
            _campanhaService.alterarValor(cod, valorNovo);
            return status(204).build();
        } catch (IllegalArgumentException ex) {
            return status(404).build();
        }
    }

    @DeleteMapping("/{cod}")
    public ResponseEntity<?> apagarCampanha(@PathVariable Integer cod) {
        if (cod == null || cod <= 0)
            return status(400).build();

        try {
            _campanhaService.apagarCampanha(cod);
            return status(204).build();
        } catch (IllegalArgumentException ex) {
            return status(404).build();
        }
    }

    @GetMapping("/{cod}")
    public ResponseEntity<ReadCampanhaDto> getCampanhaById(@PathVariable Integer cod){
        if (cod == null || cod <= 0)
            return status(400).build();

        ReadCampanhaDto campanha = _campanhaService.getCampanhaById(cod);

        return campanha == null ? status(404).build() : status(200).body(campanha);
    }

    @PatchMapping("/disponivel/{idCampanha}")
    public ResponseEntity<?> alterarDisponibilidadeCampanha(@PathVariable Integer idCampanha){
        if (idCampanha == null || idCampanha <= 0)
            return status(400).build();

        try {
            _campanhaService.alterarDisponibilidadeCampanha(idCampanha);
            return status(204).build();
        } catch (NullPointerException ex){
            return status(404).build();
        }catch (Exception ex){
            return status(500).body(ex.getMessage());
        }
    }

    @GetMapping("/recomendacao/{idCampanha}")
    public ResponseEntity<List<ReadCampanhaDto>> getRecomendacoesByIdCampanha(@PathVariable @Positive int idCampanha){
        try {
            List<ReadCampanhaDto> recomendacoes = _campanhaService.getRecomendacoesByIdCampanha(idCampanha);
            return  recomendacoes.isEmpty() ? status(204).build() : status(200).body(recomendacoes);
        }catch (NullPointerException ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }

    }
}