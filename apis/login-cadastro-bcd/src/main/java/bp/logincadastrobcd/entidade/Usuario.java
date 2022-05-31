package bp.logincadastrobcd.entidade;

import javax.persistence.*;
import javax.validation.constraints.*;

@MappedSuperclass
public abstract class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod;

    @NotNull @NotBlank @Size(min = 2, max = 45)
    private String nome;

    @Email @NotNull @NotBlank
    private String email;

    @NotNull @NotBlank @Size(min = 8, max = 16)
    private String senha;

    @NotNull @NotBlank @Size(min = 2, max = 20)
    private String usuario;

    @NotNull @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})") //Exemplo: (11) 92005-7526
    private String telefone;

    @NotNull
    private boolean autenticado;

    @Column(length = 50_000_000)
    private byte[] fotoPerfil;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String usuario, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
    }

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

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
