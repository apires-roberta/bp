package bp.logincadastrobcd.dto.ong;

import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;

public class ReadOngDto extends ReadUsuarioDto {
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
