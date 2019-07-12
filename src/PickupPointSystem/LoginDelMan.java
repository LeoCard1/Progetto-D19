package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.PersistenceFacade;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryTable;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryManTable;
import PickupPointSystem.DatabaseSystem.Tables.PackageTable;
import PickupPointSystem.Server.NotificationSystem.PickupMail;

import java.io.IOException;
import java.util.ArrayList;

/** This class manages the delivery man log in
 * @author Andrea Stella
 * @version 1.0
 */

public class LoginDelMan{

    private PersistenceFacade facade = new PersistenceFacade();
    private PickupPoint pickupPoint;
    private String delID;

    /**
     * The constructor.
     * @param pickupPoint the PickupPoint where the delivery man must access
     */

    public LoginDelMan(PickupPoint pickupPoint){
        this.pickupPoint = pickupPoint;
    }

    /**
     * This method authenticates the DeliveryMan
     * @param delID the delivery man id
     * @param password the delivery man password
     * @return true if the credentials are correct, else false
     */

    public boolean login(String delID, String password) throws IOException {
        DeliveryManTable del = facade.getDeliveryMan(delID);
        
        if (del != null && del.getPassword().equals(password)){
            this.delID = delID;
            return true;
        }
        return false;
    }

    /**
     * This method adds the DeliveryMan packages that is authenticated.
     */

    public void addDeliverymanPackages() throws IOException {
        for(DeliveryTable delivery : facade.getDeliveries(pickupPoint.getId())){
            if(!delivery.wasMade() && delivery.hasDelID(delID)){
                pickupPoint.addPackage(facade.getPackage(delivery.getPackID()));
            }
        }
    }

    /**
     * This method empties the box that have inside them a package delivered
     * for at least 3 days by the delivery man who made the access and sends
     * the pick up mail
     */

    public void pickupPackages() throws IOException {
        ArrayList<DeliveryTable> deliveries = facade.getDeliveries(pickupPoint.getId());
        for(DeliveryTable delivery : deliveries){
            if(delivery.hasDelID(delID) && delivery.wasMade() && delivery.hasPackDeliveredForThreeDays()){
                String packID = delivery.getPackID();
                String email = facade.getPackage(packID).getCustomerEmail();
                PickupMail notify = new PickupMail(email, packID);
                notify.start();
                pickupPoint.emptyBox(delivery.getBoxPassword());
            }
        }
    }

}
