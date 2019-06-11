package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.PersistenceFacade;
import PickupPointSystem.DatabaseSystem.Tables.Delivery;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;
import PickupPointSystem.DatabaseSystem.Tables.Package;
import PickupPointSystem.LockerSystem.BoxType.Box;
import PickupPointSystem.Server.NotificationSystem;

import java.io.IOException;
import java.util.ArrayList;


public class LoginDelMan{

    private PersistenceFacade facade = new PersistenceFacade();
    private PickupPoint pickupPoint;
    private String delID;

    public LoginDelMan(PickupPoint pickupPoint){
        this.pickupPoint = pickupPoint;
    }

    public boolean login(String delID, String password){
        DeliveryMan del = facade.getDeliveryMan(delID);

        if (del != null && del.getPassword().equals(password)){
            this.delID = delID;
            return true;
        }
        return false;
    }

    public String addDeliverymanPackages() throws IOException {
        ArrayList<Package> packages = facade.getPackagesFromDelID(pickupPoint.getId(), delID);
        String message ="Parcels to be delivered:\n\n";
        for(Package pack : packages){
            int boxNumber = pickupPoint.addPackage(pack);
            message+= pack.getId() + "\t" + boxNumber+"\n";
        }

        return message;
    }

    public String getPackagesToPickup(){
        ArrayList<Delivery> deliveries = facade.getDeliveries(pickupPoint.getId());
        String message = "Parcels to pickup:\n\n";
        for(Delivery delivery : deliveries){
            if(delivery.wasMade() && delivery.hasPackDeliveredForThreeDays()){
                String packID = delivery.getPackID();
                String email = facade.getPackage(packID).getCustomerEmail();
                NotificationSystem notify = new NotificationSystem();
                notify.sendPickupMail(email,packID);
                pickupPoint.emptyBox(delivery.getBoxPassword());
                message+= packID + "\t" + delivery.getBoxNumber()+"\n";
            }
        }
        return message;
    }


}
