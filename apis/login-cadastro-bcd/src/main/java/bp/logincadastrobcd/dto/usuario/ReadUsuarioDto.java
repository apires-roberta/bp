package bp.logincadastrobcd.dto.usuario;

import java.time.LocalDate;

public class ReadUsuarioDto {
    private Integer cod;
    private String nome, email, usuario, telefone;
    private byte[] fotoPerfil;
    private LocalDate dataNascimento;
    private String bio;
    private byte[] fotoCapa;

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

    public byte[] getFotoPerfil() { return fotoPerfil; }

    public Integer getCod() { return cod; }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getBio() {
        return bio;
    }

    public byte[] getFotoCapa() {
        return fotoCapa;
    }
}