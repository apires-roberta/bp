package com.bp.feedbcd.entidade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class NotificacaoFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Inscricao inscricao;

    private LocalDateTime dataNotificacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotificacaoFeed() {
        dataNotificacao = LocalDateTime.now();
    }

    public NotificacaoFeed(Inscricao inscricao) {
        this.inscricao = inscricao;
        dataNotificacao = LocalDateTime.now();
    }

    public void setDataNotificacao(LocalDateTime dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }

    public LocalDateTime getDataNotificacao() {
        return dataNotificacao;
    }
}