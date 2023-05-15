package comMain.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Email {
    private final String from;
    private final String to;
    private final String sub;
    private final String content;
    private byte[] image;


    /**
     * @param to      - email adress of the resder you send the mail to
     * @param sub     - the subject of the mail
     * @param content - the content of the mail
     */
    public Email(String to, String sub, String content) {

        this.from = "labiblioteca23@gmail.com";
        this.to = to;
        this.sub = sub;
        this.content = content;

    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * this function insert the entire properties to the email, and send it
     */
    public void SendEmail() {
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");

        Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("labiblioteca23@gmail.com", "cwbgakzcmcugonkd");
            }
        });
        try {


            MimeMessage m = new MimeMessage(s);
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub);

            MimeBodyPart ConPart = new MimeBodyPart();
            ConPart.setContent("<pre><font size='4' color='blue'>"  + content.replace("\n","<br>") + "</font></pre>", "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(ConPart);

            if (image != null) {
                MimeBodyPart FilePart = new MimeBodyPart();
                FilePart.setContent(image, "image/jpeg");
                FilePart.setFileName("book Picture");
                multipart.addBodyPart(FilePart);
            }


            m.setContent(multipart);

            Transport.send(m);

            System.out.println("Success...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
