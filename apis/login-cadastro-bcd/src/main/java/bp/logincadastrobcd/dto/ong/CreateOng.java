package bp.logincadastrobcd.dto.ong;

import bp.logincadastrobcd.dto.usuario.CreateUsuarioDto;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.*;

public class CreateOng extends CreateUsuarioDto {
    @CNPJ
    @NotNull
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}