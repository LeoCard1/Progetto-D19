package PickupPointSystem.Server;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotificationSystem {

    private final String username = "smartlocker.d19@gmail.com";
    private final String password = "ProgettoD19";
    private Session session;

    public NotificationSystem(){
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

    public void sendEmail(String email, String text){

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

    public void sendDeliveryMail(String email, String piPoID, String packID, String password, Date pickupDate){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String pickupDateString = sdf.format(pickupDate);

        String text = "Hello,\n\nYour parcel " +packID+ " has been delivered to PickupPoint "+piPoID+"\n\nYour pick-up code is " +
                password+"\n\nYour package will be available for pick up until the location closes on "+pickupDateString+
                "\n\nWhen you arrive at SmartLocker, enter your pick-up code and follow the instructions on the screen. " +
                "If you're unable to pick-up your parcel before the location closes on "+pickupDateString+ " it will be returned " +
                "for a full refund.\n\nWe look forward to seeing you again soon!";
        sendEmail(email, text);
    }

    public void sendPickupMail(String email, String packID){
        String text = "Hello,\n\nYour parcel " +packID+ " has been picked up away from pickup point by our delivery man " +
                "because 3 days have passed.\nThe parcel will soon be reimbursed.\n\nWe look forward to seeing you again soon!";
        sendEmail(email,text);
    }
    
}
