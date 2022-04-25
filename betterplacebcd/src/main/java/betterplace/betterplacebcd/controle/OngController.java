package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bp/ong")
public class OngController {

    @Autowired
    private OngRepository repository;

    //Metodos
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity login(@PathVariable String email,
                                @PathVariable String senha) {

        Ong ong = repository.findByEmail(email).get(0);

        if (ong.getEmail().isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        if(ong.getSenha().equals(senha)){
            ong.setAutenticado(true);
            repository.Logar(email, true);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/cadastroOng")
    public ResponseEntity cadastro(@RequestBody @Valid Ong ong) {
        repository.save(ong);
        return ResponseEntity.status(201).build();
    }

}
