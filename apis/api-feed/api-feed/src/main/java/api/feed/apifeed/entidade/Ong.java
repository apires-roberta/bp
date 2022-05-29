package api.feed.apifeed.entidade;

import javax.persistence.*;

@Entity
public class Ong {
    @Id
    private Integer cod;
    @Column(length = 50_000_000)
    private byte[] fotoPerfil;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
}
