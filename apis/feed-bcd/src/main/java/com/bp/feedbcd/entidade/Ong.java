package com.bp.feedbcd.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Ong extends Usuario {
    @Column(length = 50_000_000)
    private byte[] fotoPerfil;
    @JsonIgnore
    @ManyToMany(mappedBy = "ongs")
    private List<Doador> doadores;

//    public List<Inscricao> getInscricao() {
//        return inscricao;
//    }
//
//    public void setInscricao(List<Inscricao> inscricao) {
//        this.inscricao = inscricao;
//    }


    public List<Doador> getDoadores() {
        return doadores;
    }

    public void setDoadores(List<Doador> doadores) {
        this.doadores = doadores;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getNomeOng() {
        return super.getNome();
    }
}
