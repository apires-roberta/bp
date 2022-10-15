package bp.logincadastrobcd.dto.doador;

import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;

public class ReadDoadorDto extends ReadUsuarioDto {

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