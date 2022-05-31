package bp.logincadastrobcd.entidade;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Doador extends Usuario {
    @CPF @NotNull
    private String cpf;

    public Doador() {
    }

    public Doador(String nome, String email, String senha, String usuario, String telefone, String cpf) {
        super(nome, email, senha, usuario, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
