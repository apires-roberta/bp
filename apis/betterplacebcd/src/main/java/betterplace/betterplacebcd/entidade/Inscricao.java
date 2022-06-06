package betterplace.betterplacebcd.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(Inscricao.class)
public class Inscricao implements Serializable {
    @Id
    public Integer fkOng;
    @Id
    public Integer fkDoador;
    @ManyToOne
    public NotificacaoFeed notificacaoFeed;

    public Inscricao(Integer fkOng, Integer fkDoador) {
        this.fkOng = fkOng;
        this.fkDoador = fkDoador;
    }

    public Inscricao() {
    }

    public Integer getFkOng() {
        return fkOng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscricao that = (Inscricao) o;
        return fkOng.equals(that.fkOng) && fkDoador.equals(that.fkDoador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkOng, fkDoador);
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }

    public Integer getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(Integer fkDoador) {
        this.fkDoador = fkDoador;
    }

    public NotificacaoFeed getNotificacaoFeed() {
        return notificacaoFeed;
    }

    public void setNotificacaoFeed(NotificacaoFeed notificacaoFeed) {
        this.notificacaoFeed = notificacaoFeed;
    }
}
