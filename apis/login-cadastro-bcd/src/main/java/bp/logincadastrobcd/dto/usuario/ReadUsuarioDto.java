package bp.logincadastrobcd.dto.usuario;

import java.net.URL;
import java.time.LocalDate;

public class ReadUsuarioDto {
    private Integer cod;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    private Integer numero;
    private String nome, email, usuario, telefone;
    private /*byte[]*/ URL fotoPerfil;
    private LocalDate dataNascimento;
    private String bio;
    private /*byte[]*/ URL fotoCapa;
    private LocalDate dataCriacaoConta;
    private String cep;

    public String getCep() {
        return cep;
    }

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

    public /*byte[]*/ URL getFotoPerfil() { return fotoPerfil; }

    public Integer getCod() { return cod; }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getBio() {
        return bio;
    }

    public /*byte[]*/ URL getFotoCapa() {
        return fotoCapa;
    }
}