package com.bp.feedbcd.services.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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

    public void enviarEmail(String assunto, String mensagem, String destinatario){
        System.out.println(destinatario);
        try {
            Message message = new MimeMessage(propriedades());
            message.setFrom(new InternetAddress("jeoveva@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(mensagem);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}