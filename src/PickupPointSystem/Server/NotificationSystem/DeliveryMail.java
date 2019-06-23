package PickupPointSystem.Server.NotificationSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryMail extends EMailSender {
    private String email;
    private String piPoID;
    private String packID;
    private String password;
    private Date pickupDate;
    private String location;
    private String address;

    public DeliveryMail(String email, String piPoID, String packID, String password, Date pickupDate, String location, String address) {
        super();

        this.email = email;
        this.piPoID = piPoID;
        this.packID = packID;
        this.password = password;
        this.pickupDate = pickupDate;
        this.location = location;
        this.address = address;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String pickupDateString = sdf.format(pickupDate);

        String text = "Hello,\n\nYour parcel " + packID + " has been delivered to PickupPointTable "+ piPoID +" in "+ address +
                ", " + location + "\n\nYour pick-up code is " + password + "\n\nYour package will be available for pick up" +
                " until the location closes on " + pickupDateString + "\n\nWhen you arrive at SmartLocker, enter your pick-up" +
                " code and follow the instructions on the screen. " + "If you're unable to pick-up your parcel before the" +
                " location closes on " + pickupDateString + " it will be returned " + "for a full refund.\n\nWe look" +
                " forward to seeing you again soon!";
        sendEmail(email, text);
    }
}
