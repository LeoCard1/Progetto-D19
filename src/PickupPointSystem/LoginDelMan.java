package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.PersistenceFacade;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;
import PickupPointSystem.DatabaseSystem.Tables.Package;
import PickupPointSystem.ObserverPattern.Observer;

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
        String message ="";
        for(Package pack : packages){
            int boxNumber = pickupPoint.addPackage(pack);
            message+= pack.getId() + "\t" + boxNumber+"\n";
        }

        return message;
    }

}
