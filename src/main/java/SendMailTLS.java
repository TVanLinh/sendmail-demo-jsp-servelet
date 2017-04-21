import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

    public static void main(String[] args) {

        final String username = "linhtran180895@gmail.com";
        final String password = "password generate from google";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("linhtran180895@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("buithao230296@gmail.com"));
            message.setSubject("Test Mail Linh Tran");
            message.setText("Hello vk yeu," + "\n\n Test send email in java!" +
                    " \n\n Haha"+"\nhttps://mail.google.com/mail/u/2/#inbox/15b8e725de26b6a5");

            Transport.send(message);

            System.out.println("Success!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}