package betterplace.betterplacebcd.data.dto.usuario;

public class ReadUsuarioDto {
    private Integer cod;
    private String nome, email, usuario, telefone;
    private byte[] fotoPerfil;

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
}
