package bp.logincadastrobcd.dto.doador;
import bp.logincadastrobcd.dto.usuario.UpdateUsuarioDto;
import org.hibernate.validator.constraints.br.CPF;

public class UpdateDoadorDto extends UpdateUsuarioDto {
    @CPF
    private String cpf;
    private double pontuacao;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }
}