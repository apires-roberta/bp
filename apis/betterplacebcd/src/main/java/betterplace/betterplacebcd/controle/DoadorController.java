package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/bp")
public class DoadorController {

    @Autowired
    private DoadorRepository repository;

    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity login(@PathVariable String email,
                                @PathVariable String senha) {

        Doador doador;

        if(repository.findByEmail(email).isEmpty()){
            return status(404).build();
        }else{
           doador = repository.findByEmail(email).get(0);
        }

        if (doador.getSenha().equals(senha) && doador.getEmail().equals(email)) {
            doador.setAutenticado(true);
            repository.Logar(email, true);
            return status(200).body(doador);
        } else {
            return status(403).build();
        }
    }

    @PostMapping("/cadastroDoador")
    public ResponseEntity cadastro(@RequestBody @Valid Doador doador) {
        if (doador.getEmail() == null || doador.getSenha() == null || doador.getCpf() == null)
            return ResponseEntity.status(400).build();
        doador.setAutenticado(false);
        repository.save(doador);
        return status(201).build();
    }

    @PatchMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario){
        Optional<Doador> doador = repository.findByCod(idUsuario);
        if(doador.isEmpty()){
            return status(204).build();
        }
        doador.get().setAutenticado(false);
        repository.save(doador.get());
        return status(201).build();
    }

    @DeleteMapping("/deletarConta/{idUsuario}")
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario){
        Optional<Doador> doador = repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return status(404).build();
        repository.delete(doador.get());
        return status(200).build();
    }
}
