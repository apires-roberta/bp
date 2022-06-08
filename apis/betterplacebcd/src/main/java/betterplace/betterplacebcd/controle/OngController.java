package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/bp/ong")
public class OngController {

    @Autowired
    private OngRepository repository;

    //Metodos
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity login(@PathVariable @Valid String email,
                                @PathVariable @Valid String senha) {
        Ong ong;

        if(repository.findByEmail(email).isEmpty()){
            return status(404).build();
        }else{
            ong = repository.findByEmail(email).get();
        }

        if (ong.getSenha().equals(senha) && ong.getEmail().equals(email)) {
            ong.setAutenticado(true);
            repository.Logar(email, true);
            return status(200).body(ong);
        } else {
            return status(403).build();
        }
    }

    @PostMapping("/cadastroOng")
    public ResponseEntity cadastro(@RequestBody @Valid Ong ong) {
        if (ong.getEmail() == null || ong.getSenha() == null || ong.getCnpj() == null)
            return ResponseEntity.status(400).build();
        ong.setAutenticado(false);
        repository.save(ong);
        return status(201).build();
    }

    @PatchMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario) {
        Optional<Ong> ong = repository.findByCod(idUsuario);
        if (ong.isEmpty()) {
            return status(404).build();
        }
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

}
