package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.entidade.Doador;
import bp.logincadastrobcd.repositorio.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/bp/doador")
public class DoadorController {

    @Autowired
    private DoadorRepository repository;
    @PostMapping("/login/{email}/{senha}")
    public ResponseEntity login(@PathVariable String email,
                                @PathVariable String senha) {
        if (!repository.existsByEmailAndSenha(email, senha))
            return status(404).build();

        Doador doador = repository.findByEmail(email);
        doador.setAutenticado(true);
        repository.save(doador);
        return status(200).body(doador.getCod());
    }

    @PostMapping("/cadastroDoador")
    public ResponseEntity cadastro(@RequestBody @Valid Doador doador) {
        if (repository.existsByEmail(doador.getEmail()))
            return status(409).build(); //409 - Conflict

        doador.setAutenticado(false);
        repository.save(doador);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario){
        if (!repository.existsByCodAndAutenticadoTrue(idUsuario))
            return status(404).build();

        Optional<Doador> doador = repository.findByCod(idUsuario);
        doador.get().setAutenticado(false);
        repository.save(doador.get());
        return status(200).build();
    }
    @DeleteMapping("/deletarConta/{idUsuario}")
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario){
        Optional<Doador> doador = repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return status(404).build();

        repository.delete(doador.get());
        return status(200).body(doador);
    }
    @GetMapping("/{idDoador}")
    public ResponseEntity getDoador(@PathVariable Integer idDoador) {
        Optional<Doador> doador = repository.findByCod(idDoador);
        if (doador.isEmpty())
            return status(404).build();

        return status(200).body(doador);
    }
}