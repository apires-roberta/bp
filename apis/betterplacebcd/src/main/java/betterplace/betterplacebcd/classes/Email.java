package betterplace.betterplacebcd.classes;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Email {
    public Session propriedades(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("jeovajeoveva@gmail.com",
                                "Saopaulo18");
                    }
                });
        session.setDebug(true);
        return session;
    }

    public void enviarEmail(String mensagem, String email){
        System.out.println(email);
        try {
            Message message = new MimeMessage(propriedades());
            Address[] toUser = InternetAddress.parse(email);
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("BP informa: Nova doação!");
            message.setText(mensagem);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
