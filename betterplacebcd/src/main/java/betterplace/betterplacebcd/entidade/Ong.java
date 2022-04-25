package betterplace.betterplacebcd.entidade;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class Ong extends Usuario {
    @CNPJ
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
