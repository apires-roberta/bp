package bp.logincadastrobcd.entidade;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Ong extends Usuario {
    @CNPJ @NotNull
    private String cnpj;

    public Ong() {
    }

    public Ong(String nome, String email, String senha, String usuario, String telefone, String cnpj) {
        super(nome, email, senha, usuario, telefone);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
