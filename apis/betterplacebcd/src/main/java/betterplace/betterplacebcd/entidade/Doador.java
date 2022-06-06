package betterplace.betterplacebcd.entidade;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Doador extends Usuario {
    @CPF @NotNull
    private String cpf;
    @ManyToOne
    private Inscricao inscricao;
    @ManyToOne
    private  NotificacaoFeed notificacoes;
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Inscricao getinscricao() {
        return inscricao;
    }

    public void setinscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }

    public NotificacaoFeed getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(NotificacaoFeed notificacoes) {
        this.notificacoes = notificacoes;
    }
}