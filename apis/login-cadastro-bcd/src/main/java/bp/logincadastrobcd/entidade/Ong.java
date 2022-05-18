package bp.logincadastrobcd.entidade;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Ong extends Usuario {
    @CNPJ @NotNull
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
