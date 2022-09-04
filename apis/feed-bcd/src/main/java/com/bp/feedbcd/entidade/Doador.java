package com.bp.feedbcd.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Doador extends Usuario {
    @JsonIgnore
    @ManyToMany
    @JoinTable (name = "Inscricao", joinColumns = @JoinColumn (name = "doador_cod"),
            inverseJoinColumns = @JoinColumn(name = "ong_cod"))
    private List<Ong> ongs;
//    public List<Inscricao> getInscricao() {
//        return inscricao;
//    }
//
//    public void setInscricao(List<Inscricao> inscricao) {
//        this.inscricao = inscricao;
//    }
//
//    public List<Ong> getOngs() {
//        return ongs;
//    }
//
//    public void setOngs(List<Ong> ongs) {
//        this.ongs = ongs;
//    }
}