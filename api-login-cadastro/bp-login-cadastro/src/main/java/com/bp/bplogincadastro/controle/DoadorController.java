package com.bp.bplogincadastro.controle;

import com.bp.bplogincadastro.entidade.Doador;
import com.bp.bplogincadastro.repositorio.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bp")
public class DoadorController {

    @Autowired
    private DoadorRepository repository;

    //Metodos
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity login(@PathVariable  String email,
                                @PathVariable String senha) {

        Doador doador = repository.findByEmail(email).get(0);

        if (doador.getEmail().isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        if(doador.getSenha().equals(senha)){
            doador.setAutenticado(true);
            repository.Logar(email, true);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/cadastroDoador")
    public ResponseEntity cadastro(@RequestBody @Valid Doador doador) {
        repository.save(doador);
        return ResponseEntity.status(201).build();
    }
}
