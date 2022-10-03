package betterplace.betterplacebcd.classes.ed;

import betterplace.betterplacebcd.classes.Email;

import java.util.Observable;
import java.util.Observer;

public class Notificacao extends Observable {
    private Boolean doacao;


    public void setDoacao(Boolean doacao) {
        this.doacao = doacao;
        setChanged();
        notifyObservers();
    }

    public void novaDoacao(String emailOng, String mensagem){
        Notificacao notificacao = new Notificacao();
        notificar assinante1 = new notificar(notificacao, emailOng, mensagem);
        notificacao.setDoacao(true);
    }
}

class notificar implements Observer {
    Observable notificacao;
    String emailOng, mensagem;

    public notificar(Observable notificacao, String emailOng, String mensagem) {
        this.notificacao = notificacao;
        this.emailOng = emailOng;
        this.mensagem = mensagem;
        notificacao.addObserver(this);
    }
    @Override
    public void update(Observable notificacao, Object arg1) {
        if (notificacao instanceof Notificacao) {
            Email email = new Email();
            email.enviarEmail(mensagem, emailOng);
        }
    }
}
