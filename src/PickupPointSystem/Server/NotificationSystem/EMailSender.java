package PickupPointSystem.Server.NotificationSystem;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * @author Gruppo D19
 * @version 1.0.0
 */

public abstract class EMailSender extends Thread {

    private final String username = "smartlocker.d19@gmail.com";
    private final String password = "ProgettoD19";
    private Session session;

    /**
     * This is the constructor that logs into its mail
     */

    public EMailSender(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    /**
     * This methods sends the actual email
     * @param email Customer's email address
     * @param text this is the body of the mail
     */
    protected void sendEmail(String email, String text){

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Smart Locker");
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
