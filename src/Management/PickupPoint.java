package Management;

import LockerSystem.BoxType.Box;
import LockerSystem.Package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class PickupPoint {
    private String id; //Codice identificativo punto di ritiro?
    private ArrayList<Box> boxList; // Box attualmente presenti nel Punto di ritiro
    private int numeroBoxUsati; // Da utilizzare nelle statistiche

    public PickupPoint(String id){
        this.id = id;
        boxList = new ArrayList<>();
        numeroBoxUsati = 0;
    }

    public PickupPoint(int numeroBoxIniziali , String id){
        this.id = id;
        boxList = new ArrayList<>(numeroBoxIniziali);
        numeroBoxUsati = 0;
    }

    public void addBox(Box box){
        boxList.add(box);
    }

    public int addPackage(Package pack) throws IOException {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize())!= -1){
                box.addPackage(pack);
                ReadWriteDeliveryDate rwf = new ReadWriteDeliveryDate() ;
                rwf.insertText(box.toString());
                return box.getCode();
            }
        }
        return 0;
    }

    public boolean removePackage(Package pack) throws IOException {
        for(Box box : boxList){
            if(box.getPack() == pack){
                ReadWriteDeliveryDate rwf = new ReadWriteDeliveryDate() ;
                rwf.removeText(box.getCode());
                box.removePackage();
                return true;
            }
        }
        return false;
    }


}
