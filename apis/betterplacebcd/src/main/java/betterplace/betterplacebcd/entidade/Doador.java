package betterplace.betterplacebcd.entidade;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Doador extends Usuario {
    @CPF @NotNull
    private String cpf;
    @PositiveOrZero
    private double pontuacao;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getPontuacao() {
        return pontuacao;
    }
}