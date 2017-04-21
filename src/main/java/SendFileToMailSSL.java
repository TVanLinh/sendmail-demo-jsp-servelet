/**
 * Created by linhtran on 21/04/2017.
 */
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
        import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
        import  javax.mail.Address;
import javax.mail.internet.MimeMultipart;

public class SendFileToMailSSL {
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

           //crete file 1 and add to messageBodyPart
            File file=new File(ClassLoader.getSystemResource("csdldemo2.sql").getFile());
            String urlfile =file.getAbsolutePath();
            String fileName = "csdldemo2.sql";
            DataSource source = new FileDataSource(urlfile);
            MimeBodyPart  messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);

            //crete file 2 and add to messageBodyPart
            File file2=new File(ClassLoader.getSystemResource("filter-maven.png").getFile());
            DataSource dataSource2=new FileDataSource(file2);
            MimeBodyPart mimeBodyPart2=new MimeBodyPart();
            mimeBodyPart2.setDataHandler(new DataHandler(dataSource2));
            mimeBodyPart2.setFileName("Hinh anh maven");

            //add mimeBodyPart,mimeBodyPart2 to multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(mimeBodyPart2);

            //add multipart to message
            message.setContent(multipart);
            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Success");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}