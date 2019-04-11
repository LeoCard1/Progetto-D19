package LockerSystem;

import java.util.ArrayList;

public class DeliveryMan {
    private String id; // id univoco che identifica un fattorino
    private ArrayList<Package> listaPacchi;
    private int packCounter; // pacchi totali consegnati. Utilizzo nei dati statistici

    public DeliveryMan(String id){
        this.id = id;
        listaPacchi = new ArrayList<>();
        packCounter = 0;
    }

    public void addPackage(Package pack){
        listaPacchi.add(pack);
        packCounter++;
    }

    public void removePackage(Package pack){
        listaPacchi.remove(pack);
    }

    public int getPackCounter() {
        return packCounter;
    }
}
