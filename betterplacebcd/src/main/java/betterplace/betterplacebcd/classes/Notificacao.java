package betterplace.betterplacebcd.classes;

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
        Notificacao revistaInformatica = new Notificacao();
        notificar assinante1 = new notificar(revistaInformatica, emailOng, mensagem);
        revistaInformatica.setDoacao(true);
    }
}

class notificar implements Observer {
    Observable revistaInformatica;
    String emailOng, mensagem;

    public notificar(Observable revistaInformatica, String emailOng, String mensagem) {
        this.revistaInformatica = revistaInformatica;
        this.emailOng = emailOng;
        System.out.println(emailOng);
        this.mensagem = mensagem;
        revistaInformatica.addObserver(this);
    }
    @Override
    public void update(Observable revistaInfSubject, Object arg1) {
        if (revistaInfSubject instanceof Notificacao) {
            Email email = new Email();
            email.enviarEmail(mensagem, emailOng);
        }
    }
}
