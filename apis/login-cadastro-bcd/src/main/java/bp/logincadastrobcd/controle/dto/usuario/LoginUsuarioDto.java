package bp.logincadastrobcd.controle.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUsuarioDto {
    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull @NotBlank @Size(min = 8, max = 16)
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

}
