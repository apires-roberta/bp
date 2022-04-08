package com.bp.bplogincadastro.entidade;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Doador extends Usuario {

    //Atributos
    @CPF // valida cpf
    private String cpf;

    //Construtor
    public Doador(Long cod,
                  String nome,
                  String email,
                  String senha,
                  String usuario,
                  String telefone,
                  String cpf) {
        super(cod,
                nome,
                email,
                senha,
                usuario,
                telefone);
        this.cpf = cpf;
    }

    public Doador() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
