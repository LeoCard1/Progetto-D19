package Management;

import LockerSystem.BoxType.Box;
import LockerSystem.Package;

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

    public boolean addPackage(Package pack){
        Collections.sort(listaBox);
        for(Box box : listaBox){
            if(box.isAvailability() && box.getSize().compareTo(pack.getSize())!= -1){
                box.addPackage(pack);
                return true;
            }
        }
        return false;
    }

    // l'ho aggiunto solo per testare l'addPackage, potete cancellarlo se volete
    public String toStringBox(){
        String s="";
        for(Box box : listaBox){
            s+= box.toString()+"\n";
        }
        return s;
    }

}
