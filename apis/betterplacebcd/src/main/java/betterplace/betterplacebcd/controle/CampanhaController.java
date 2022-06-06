package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/campanha")
public class CampanhaController{
    @Autowired
    private CampanhaRepository repository;

    @PostMapping
    public ResponseEntity postCampanha(@RequestBody @Valid Campanha novaCampanha) {
        novaCampanha.setDataCriacao(LocalDateTime.now());
        repository.save(novaCampanha);
        return ResponseEntity.status(201).build();
    }
    @GetMapping
    public ResponseEntity getTudo(){
        List<Campanha> novaCampanha = repository.findAll();
        if (novaCampanha.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(novaCampanha);
    }

    @PatchMapping("/alterarValor/{cod}/{valorNovo}")
    public ResponseEntity alterarValor(@PathVariable Integer cod, @PathVariable Double valorNovo){
        List<Campanha> campanha = repository.findByIdCampanha(cod);
        if(campanha.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        campanha.get(0).setValorNecessario(valorNovo);
        repository.save(campanha.get(0));
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{cod}")
    public ResponseEntity apagarVakinha(@PathVariable Integer cod){
        repository.delete(repository.getById(cod));
        return ResponseEntity.status(201).build();
    }

}
