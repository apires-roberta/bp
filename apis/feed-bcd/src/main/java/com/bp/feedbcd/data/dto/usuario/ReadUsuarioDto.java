package com.bp.feedbcd.data.dto.usuario;

import java.time.LocalDate;

public class ReadUsuarioDto {
    private Integer cod;
    private String nome, email, usuario, telefone;
    private byte[] fotoPerfil;
    private LocalDate dataNascimento;

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
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public Integer getCod() {
        return cod;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}