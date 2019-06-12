package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.PersistenceFacade;
import PickupPointSystem.DatabaseSystem.Tables.Delivery;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;
import PickupPointSystem.DatabaseSystem.Tables.Package;
import PickupPointSystem.Server.EMailSender;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Andrea Stella
 * @version 1.0
 */


public class LoginDelMan{

    private PersistenceFacade facade = new PersistenceFacade();
    private PickupPoint pickupPoint;
    private String delID;

    /**
     * The constructor.
     * @param pickupPoint
     */

    public LoginDelMan(PickupPoint pickupPoint){
        this.pickupPoint = pickupPoint;
    }

    /**
     * This method authenticates the DeliveryMan.
     * @param delID
     * @param password
     * @return true if the credentials are correct, else false.
     */

    public boolean login(String delID, String password){
        DeliveryMan del = facade.getDeliveryMan(delID);

        if (del != null && del.getPassword().equals(password)){
            this.delID = delID;
            return true;
        }
        return false;
    }

    /**
     * This method adds the DeliveryMan packages that is authenticated.
     * @return a message containing the pack id to deliver and the box number.
     * @throws IOException
     */

    public String addDeliverymanPackages() throws IOException {
        ArrayList<Package> packages = facade.getPackagesFromDelID(pickupPoint.getId(), delID);
        String message ="Parcels to be delivered:\n\n";
        for(Package pack : packages){
            int boxNumber = pickupPoint.addPackage(pack);
            message+= pack.getId() + "\t" + boxNumber+"\n";
        }
        return message;
    }

    /**
     * This method empties the box that have inside them a package delivered
     * for at least 3 days and send the pick up mail.
     * @return a message containing the pack id to pick up and the box number.
     */

    public String getPackagesToPickup(){
        ArrayList<Delivery> deliveries = facade.getDeliveries(pickupPoint.getId());
        String message = "Parcels to pickup:\n\n";
        for(Delivery delivery : deliveries){
            if(delivery.wasMade() && delivery.hasPackDeliveredForThreeDays()){
                String packID = delivery.getPackID();
                String email = facade.getPackage(packID).getCustomerEmail();
                EMailSender notify = new EMailSender();
                notify.sendPickupMail(email,packID);
                pickupPoint.emptyBox(delivery.getBoxPassword());
                message+= packID + "\t" + delivery.getBoxNumber()+"\n";
            }
        }
        return message;
    }


}
