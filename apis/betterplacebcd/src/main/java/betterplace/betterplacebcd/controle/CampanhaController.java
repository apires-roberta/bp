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
    public ResponseEntity postCampanha(@RequestBody @Valid CreateCampanhaDto novaCampanha) {
        if (novaCampanha.getNomeCampanha() == null || novaCampanha.getDescCampanha() == null || novaCampanha.getValorNecessario() == null)
            return ResponseEntity.status(400).build();
        try {
            _campanhaService.postCampanha(novaCampanha);
            return status(201).build();
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity getAllCampanhas() {
        List<ReadCampanhaDto> campanhas = _campanhaService.getAllCampanhas();
        if (campanhas == null)
            return status(204).build();

        return status(200).body(campanhas);
    }

    @PatchMapping("/alterarValor/{cod}/{valorNovo}")
    public ResponseEntity alterarValor(@PathVariable Integer cod, @PathVariable Double valorNovo) {
        try {
            _campanhaService.alterarValor(cod, valorNovo);
            return status(204).build();
        }catch (IllegalArgumentException ex){
            return status(404).build();
        }
    }

    @DeleteMapping("/{cod}")
    public ResponseEntity apagarCampanha(@PathVariable Integer cod) {
        try {
            _campanhaService.apagarCampanha(cod);
            return status(204).build();
        }catch (IllegalArgumentException ex){
            return status(404).build();
        }
    }
}