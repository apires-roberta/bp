package com.bp.feedbcd.entidade;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;

@MappedSuperclass
public abstract class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod;

    @NotNull @NotBlank @Size(min = 2, max = 45)
    private String nome;

    @Email @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank @Size(min = 2, max = 20)
    private String usuario;

    @NotNull @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
    private String telefone;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}