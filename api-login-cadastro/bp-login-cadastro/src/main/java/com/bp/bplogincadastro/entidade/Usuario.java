package com.bp.bplogincadastro.entidade;

import org.springframework.http.ResponseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Usuario {
    //Atributos

    @Id // do pacote javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod;

    @NotNull// Deixar obrigatorio, do pacote. javax.persistence
    @NotEmpty // Não deixa passar respostas em vazio Ex: ""
    @NotBlank // apenas para texto (String)
    @Size(min = 2, max = 15)
    private String nome;

    @Email
    private String email;
    private String senha;
    private String usuario;
    private String telefone;

    @NotNull
    private boolean autenticado;

    //Construtor
    public Usuario(Long cod, String nome, String email, String senha, String usuario, String telefone) {
        this.cod = cod;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
    }

    public Usuario() {
    }

    //Metodos
    //public abstract ResponseEntity login(String email, String senha);

    //public abstract ResponseEntity cadastro();

    //Getters and Setters
    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
