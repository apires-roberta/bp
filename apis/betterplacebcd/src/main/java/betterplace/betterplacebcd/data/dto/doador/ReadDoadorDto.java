package betterplace.betterplacebcd.data.dto.doador;


import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;

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