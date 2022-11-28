package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.services.campanha.ICampanhaService;
import feign.FeignException;
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

    @GetMapping("/ong/{fkOng}")
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

    @PatchMapping("/alterarValor/campanha/{cod}/valor/{valorNovo}")
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
        }catch (IllegalStateException ex){
            return status(422).body(ex.getMessage()); //422 — A entidade existe, mas o servidor se recusa a performar a ação. Nesse caso é pq se a campanha já recebeu uma doação, ela não pode ser deletada, apenas indisponibilizada
        }
    }

    @GetMapping("/{cod}")
    public ResponseEntity<ReadCampanhaDto> getCampanhaById(@PathVariable Integer cod){
        if (cod == null || cod <= 0)
            return status(400).build();

        ReadCampanhaDto campanha = _campanhaService.getCampanhaById(cod);

        return campanha == null ? status(404).build() : status(200).body(campanha);
    }

    @PatchMapping("/disponibilidade/{idCampanha}")
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

    @GetMapping("/disponiveis/ong/{idOng}")
    public ResponseEntity<List<ReadCampanhaDto>> getCampanhasDisponiveis(@PathVariable Integer idOng){
        if (idOng == null || idOng <= 0)
            return status(400).build();

        try {
            List<ReadCampanhaDto> campanhas = _campanhaService.getCampanhasDisponiveisByOng(idOng);
            return campanhas.isEmpty() ? status(204).build() : status(200).body(campanhas);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }
    }

    @PatchMapping("/indisponivel/ong/{idOng}")
    public ResponseEntity<?> indisponibilizarTodasCampanhasByOng(@PathVariable Integer idOng){
        if (idOng == null || idOng <= 0)
            return status(400).build();

        try {
            _campanhaService.indisponibilizarTodasCampanhasByOng(idOng);
            return status(204).build();
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (Exception ex){
            return status(500).body(ex.getMessage());
        }
    }

    @GetMapping("/quantidade-disponiveis/ong/{idOng}")
    public ResponseEntity<Integer> getQuantidadeCampanhasDisponiveisByOng(@PathVariable Integer idOng){
        if (idOng == null || idOng <= 0)
            return status(400).build();

        try {
            Integer qtdDisponiveis = _campanhaService.getQuantidadeCampanhasDisponiveisByOng(idOng);
            return status(200).body(qtdDisponiveis);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/quantidade/ong/{idOng}")
    public ResponseEntity<Integer> getQuantidadeCampanhasTotalByOng(@PathVariable Integer idOng){
        if (idOng == null || idOng <= 0)
            return status(400).build();

        try {
            Integer qtdTotal = _campanhaService.getQuantidadeCampanhasTotalByOng(idOng);
            return status(200).body(qtdTotal);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/recomendacao-PorDoacoes/campanha/{idCampanha}/doador/{idDoador}")
    public ResponseEntity<List<ReadCampanhaDto>> getRecomendacoesPorDoacoesByIdCampanha(@PathVariable @Positive int idCampanha, @PathVariable @Positive int idDoador){
        try {
            List<ReadCampanhaDto> recomendacoes = _campanhaService.getRecomendacoesPorDoacoesByIdCampanha(idCampanha, idDoador);
            return  recomendacoes.isEmpty() ? status(204).build() : status(200).body(recomendacoes);
        }catch (NullPointerException ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}