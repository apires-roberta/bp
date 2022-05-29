package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.entidade.Ong;
import bp.logincadastrobcd.repositorio.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/bp/ong")
public class OngController {
    @Autowired
    private OngRepository repository;
    @PostMapping("/login/{email}/{senha}")
    public ResponseEntity login(@PathVariable @Valid String email,
                                @PathVariable @Valid String senha) {
        if (!repository.existsByEmailAndSenha(email, senha))
            return status(404).build();

        Ong ong = repository.findByEmail(email);
        ong.setAutenticado(true);
        repository.save(ong);
        return status(200).body(ong.getCod());
    }
    @PostMapping("/cadastroOng")
    public ResponseEntity cadastro(@RequestBody @Valid Ong ong) {
        if (repository.existsByEmail(ong.getEmail()))
            return status(409).build(); //409 - Conflict

        ong.setAutenticado(false);
        repository.save(ong);
        return status(201).build();
    }
    @DeleteMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario) {
        if (!repository.existsByCodAndAutenticadoTrue(idUsuario))
            return status(404).build();

        Optional<Ong> ong = repository.findByCod(idUsuario);
        ong.get().setAutenticado(false);
        repository.save(ong.get());
        return status(200).build();
    }
    @DeleteMapping("/deletarConta/{idUsuario}")
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario) {
        Optional<Ong> ong = repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return status(404).build();

        repository.delete(ong.get());
        return status(200).build();
    }
    @GetMapping("/{idOng}")
    public ResponseEntity getOng(@PathVariable Integer idOng) {
        Optional<Ong> ong = repository.findByCod(idOng);
        if (ong.isEmpty())
            return status(404).build();

        return status(200).body(ong);
    }
}
