package com.bp.feedbcd.data.dto.usuario;

import java.net.URL;
import java.time.LocalDate;

public class ReadUsuarioDto {
    private Integer cod;
    private String nome, email, usuario, telefone;
    private /*URL*/ URL fotoPerfil;
    private LocalDate dataNascimento;
    private String bio;
    private /*URL*/ URL fotoCapa;
    private LocalDate dataCriacaoConta;

    public LocalDate getDataCriacaoConta() {
        return dataCriacaoConta;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public /*URL*/ URL getFotoPerfil() { return fotoPerfil; }

    public Integer getCod() { return cod; }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getBio() {
        return bio;
    }

    public /*URL*/ URL getFotoCapa() {
        return fotoCapa;
    }
}