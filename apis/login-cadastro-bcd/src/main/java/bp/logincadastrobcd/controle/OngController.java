package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.entidade.Ong;
import bp.logincadastrobcd.repositorio.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
            return ResponseEntity.status(404).build();
        }else{
            ong = repository.findByEmail(email).get(0);
        }

        if (ong.getSenha().equals(senha) && ong.getEmail().equals(email)) {
            ong.setAutenticado(true);
            repository.Logar(email, true);
            return ResponseEntity.status(200).body(ong);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/cadastroOng")
    public ResponseEntity cadastro(@RequestBody @Valid Ong ong) {
        ong.setAutenticado(false);
        repository.save(ong);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario) {
        List<Ong> ong = repository.findByCod(idUsuario);
        if (ong.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        ong.get(0).setAutenticado(false);
        repository.save(ong.get(0));
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/deletarConta/{idUsuario}")
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario) {
        repository.delete(repository.getById(idUsuario));
        return ResponseEntity.status(201).build();
    }

}
