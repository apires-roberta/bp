package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Vakinha;
import betterplace.betterplacebcd.repositorio.VakinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vakinha")
public class VakinhaController{
    @Autowired
    private VakinhaRepository repository;

    @PostMapping
    public ResponseEntity postVakinha(
            @RequestBody Vakinha novaVakinha) {
        repository.save(novaVakinha);
        return ResponseEntity.status(201).build();
    }
    @GetMapping
    public ResponseEntity getTudo(){
        List<Vakinha> novaVakinha = repository.findAll();
        if (novaVakinha.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(novaVakinha);
    }

}
