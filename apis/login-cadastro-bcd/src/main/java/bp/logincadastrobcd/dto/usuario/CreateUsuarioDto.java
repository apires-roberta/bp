package bp.logincadastrobcd.dto.usuario;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CreateUsuarioDto {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 45)
    private String nome;

    @Email
    @NotNull @NotBlank
    private String email;

    @NotNull @NotBlank @Size(min = 8, max = 16)
    private String senha;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    private String usuario;

    @NotNull
    @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})") //Exemplo: (11) 92005-7526
    private String telefone;
    @NotNull
    @Positive
    private String cep;

    @NotNull
    private Integer numero;

    @PastOrPresent
    @NotNull
    private LocalDate dataNascimento;
    private String bio;
    private byte[] fotoCapa;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getFotoCapa() {
        return fotoCapa;
    }

    public void setFotoCapa(byte[] fotoCapa) {
        this.fotoCapa = fotoCapa;
    }
}