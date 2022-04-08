package com.bp.bplogincadastro.entidade;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;

@Entity
public class Ong extends Usuario{

    @CNPJ // verifica cnpj
    private String cnpj;

    public Ong(Long cod,
               String nome,
               String email,
               String senha,
               String usuario,
               String telefone,
               String cnpj) {
        super(cod, nome, email, senha, usuario, telefone);
        this.cnpj = cnpj;
    }

    public Ong() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
