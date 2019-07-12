package PickupPointSystem.Server.NotificationSystem;

/**
 * @author Gruppo D19
 * @version 1.0.0
 */

public class PickupMail extends EMailSender {
    private String email;
    private String packID;

    /**
     * This is the constructor
     *
     * @param email Customer's email
     * @param packID Pack's ID
     */

    public PickupMail(String email, String packID) {
        super();

        this.email = email;
        this.packID = packID;
    }

    /**
     * This method sends the emails
     */

    @Override
    public void run() {
        String text = "Hello,\n\nYour parcel " + packID + " has been picked up away from the pickup point by our delivery man " +
                "because 3 days have passed.\nThe parcel will soon be reimbursed.\n\nWe look forward to seeing you again soon!";
        sendEmail(email,text);
    }
}
