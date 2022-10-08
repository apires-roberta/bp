package bp.logincadastrobcd.dto.doador;
import bp.logincadastrobcd.dto.usuario.UpdateUsuarioDto;
import org.hibernate.validator.constraints.br.CPF;

public class UpdateDoadorDto extends UpdateUsuarioDto {
    @CPF
    private String cpf;

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}