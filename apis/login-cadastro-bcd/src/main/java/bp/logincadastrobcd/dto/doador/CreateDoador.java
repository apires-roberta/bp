package bp.logincadastrobcd.dto.doador;

import bp.logincadastrobcd.dto.usuario.CreateUsuarioDto;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public class CreateDoador extends CreateUsuarioDto {
    @CPF
    @NotNull
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}