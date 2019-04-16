package Management;

import LockerSystem.BoxType.Box;
import LockerSystem.Package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class PickupPoint {
    private String id; //Codice identificativo punto di ritiro?
    private ArrayList<Box>listaBox; // Box attualmente presenti nel Punto di ritiro
    private int numeroBoxUsati; // Da utilizzare nelle statistiche

    public PickupPoint(String id){
        this.id = id;
        listaBox = new ArrayList<>();
        numeroBoxUsati = 0;
    }

    public PickupPoint(int numeroBoxIniziali , String id){
        this.id = id;
        listaBox = new ArrayList<>(numeroBoxIniziali);
        numeroBoxUsati = 0;
    }

    public void addBox(Box box){
        listaBox.add(box);
    }

    public boolean addPackage(Package pack) throws IOException {
        Collections.sort(listaBox);
        for(Box box : listaBox){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize())!= -1){
                box.addPackage(pack);
                WriteFile writeDeliveryDate = new WriteFile("Archive/DeliveryDate", box.toString()) ;
                return true;
            }
        }
        return false;
    }


}
