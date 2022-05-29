package bp.logincadastrobcd.dto.doador;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public class CreateDoador {
    @NotNull @NotBlank
    @Size(min = 2, max = 45)
    private String nome;

    @Email
    @NotNull @NotBlank
    private String email;

    @NotNull @NotBlank @Size(min = 8, max = 16)
    private String senha;

    @NotNull @NotBlank @Size(min = 2, max = 20)
    private String usuario;

    @NotNull @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})") //Exemplo: (11) 92005-7526
    private String telefone;

    @CPF
    @NotNull
    private String cpf;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
