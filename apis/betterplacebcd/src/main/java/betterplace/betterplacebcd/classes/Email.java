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
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("jeoveva@outlook.com",
                                "S@opaulo18");
                    }
                });
        session.setDebug(true);
        return session;
    }

    public void enviarEmail(String mensagem, String email){
        System.out.println(email);
        try {
            Message message = new MimeMessage(propriedades());
            message.setFrom(new InternetAddress("bpGrupo06@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("BP informa: Nova doação!");
            message.setText(mensagem);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("bpGrupo06@outlook.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("juansaopaulo17@gmail.com"));
                message.setSubject("Test");
                message.setText("HI");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
 */
