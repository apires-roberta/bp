package betterplace.betterplacebcd.entidade;

import betterplace.betterplacebcd.data.dto.inscricao.InscricaoId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Embeddable
@IdClass(InscricaoId.class)
public class Inscricao implements Serializable {
    @Id
    @ManyToOne
    public Ong ong;
    @Id
    @ManyToOne
    public Doador doador;

    public Inscricao(Ong ong, Doador doador) {
        this.ong = ong;
        this.doador = doador;
    }

    public Inscricao() {
    }

    public Ong getOng() {
        return ong;
    }
    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscricao inscricao = (Inscricao) o;
        return ong.equals(inscricao.ong) && doador.equals(inscricao.doador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ong, doador);
    }
}