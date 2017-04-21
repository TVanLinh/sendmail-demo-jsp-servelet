/**
 * Created by linhtran on 21/04/2017.
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import  javax.mail.Address;
public class SendMailSSL {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("linhtran180895@gmail.com", "password generate from google");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("linhtran180895@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("linhtv@newayict.com"));
            message.setSubject("Test mail with SSL");
            message.setText("Hello my woman," + "\n\n Hhehehe!");

            Transport.send(message);

            System.out.println("Success");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}