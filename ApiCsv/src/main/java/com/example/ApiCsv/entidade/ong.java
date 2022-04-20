package com.example.ApiCsv.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOng;
    private String nomeOng;

    public Integer getIdOng() {
        return idOng;
    }

    public void setIdOng(Integer idOng) {
        this.idOng = idOng;
    }

    public String getNomeOng() {
        return nomeOng;
    }

    public void setNomeOng(String nomeOng) {
        this.nomeOng = nomeOng;
    }

    public String getCnpjOng() {
        return cnpjOng;
    }

    public void setCnpjOng(String cnpjOng) {
        this.cnpjOng = cnpjOng;
    }

    private String cnpjOng;
}
