package bp.logincadastrobcd.dto.ong;
import bp.logincadastrobcd.dto.usuario.UpdateUsuarioDto;
import org.hibernate.validator.constraints.br.CNPJ;

public class UpdateOngDto extends UpdateUsuarioDto {
    @CNPJ
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}