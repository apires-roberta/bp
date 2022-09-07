package bp.logincadastrobcd.entidade;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
public class Doador extends Usuario {
    @CPF @NotNull
    private String cpf;
    @PastOrPresent
    private LocalDate dataNascimento;

    public Doador() {
    }

    public Doador(String nome, String email, String senha, String usuario, String telefone, String cpf, LocalDate dataNascimento) {
        super(nome, email, senha, usuario, telefone);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
