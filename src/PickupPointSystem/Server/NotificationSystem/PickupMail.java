package PickupPointSystem.Server.NotificationSystem;

public class PickupMail extends EMailSender {
    private String email;
    private String packID;

    public PickupMail(String email, String packID) {
        super();

        this.email = email;
        this.packID = packID;
    }

    @Override
    public void run() {
        String text = "Hello,\n\nYour parcel " + packID + " has been picked up away from the pickup point by our delivery man " +
                "because 3 days have passed.\nThe parcel will soon be reimbursed.\n\nWe look forward to seeing you again soon!";
        sendEmail(email,text);
    }
}
