package LockerSystem;

import java.util.ArrayList;

public class DeliveryMan {
    private String id; // id univoco che identifica un fattorino
    private ArrayList<Package>listaPacchi;

    public DeliveryMan(String id){
        this.id = id;
        listaPacchi = new ArrayList<>();
    }

    public void addPackage(Package pacco){
        listaPacchi.add(pacco);
    }

    public void removePackage(Package pacco){
        listaPacchi.remove(pacco);
    }
}
