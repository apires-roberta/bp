package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bp")
public class DoadorController {

    @Autowired
    private DoadorRepository repository;

    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity login(@PathVariable String email,
                                @PathVariable String senha) {

        Doador doador = repository.findByEmail(email).get(0);

        if (doador.getEmail().isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        if(doador.getSenha().equals(senha)){
            doador.setAutenticado(true);
            repository.Logar(email, true);
            return ResponseEntity.status(200).body(doador);
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/cadastroDoador")
    public ResponseEntity cadastro(@RequestBody @Valid Doador doador) {
        doador.setAutenticado(false);
        repository.save(doador);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario){
        List<Doador> doador = repository.findByCod(idUsuario);
        if(doador.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        doador.get(0).setAutenticado(false);
        repository.save(doador.get(0));
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/deletarConta/{idUsuario}")
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario){
        repository.delete(repository.getById(idUsuario));
        return ResponseEntity.status(201).build();
    }
}
