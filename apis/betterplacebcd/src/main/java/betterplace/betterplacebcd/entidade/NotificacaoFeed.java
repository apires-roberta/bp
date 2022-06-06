package betterplace.betterplacebcd.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class NotificacaoFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer fkOng;
    @NotNull
    private Integer fkDoador;
    @NotNull
    private LocalDateTime dataNotificacao;

    public NotificacaoFeed() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }

    public void setFkDoador(Integer fkDoador) {
        this.fkDoador = fkDoador;
    }

    public void setDataNotificacao(LocalDateTime dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Integer getFkDoador() {
        return fkDoador;
    }

    public Integer getFkOng() {
        return fkOng;
    }

    public LocalDateTime getDataNotificacao() {
        return dataNotificacao;
    }
}