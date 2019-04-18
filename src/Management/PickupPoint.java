package Management;

import LockerSystem.BoxType.*;
import LockerSystem.Package;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class PickupPoint {
    private String id;
    private ArrayList<Box> boxList;
    private int numSmallBox;
    private int numMediumBox;
    private int numLargeBox;


    public PickupPoint(String id, int numSmallBox, int numMediumBox, int numLargeBox){
        this.id = id;
        this.numSmallBox = numSmallBox;
        this.numMediumBox = numMediumBox;
        this.numLargeBox = numLargeBox;
        boxList = new ArrayList<>();
        for(int i = 0; i < numSmallBox; i++){
            boxList.add(new SmallBox());
        }
        for(int i = 0; i < numMediumBox; i++){
            boxList.add(new MediumBox());
        }
        for(int i = 0; i < numLargeBox; i++){
            boxList.add(new LargeBox());
        }
    }

    public ArrayList<Box> getBoxList() {
        return boxList;
    }

    public int getNumSmallBox() {
        return numSmallBox;
    }

    public int getNumMediumBox() {
        return numMediumBox;
    }

    public int getNumLargeBox() {
        return numLargeBox;
    }

    public int addPackage(Package pack) throws IOException {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize())!= -1){
                box.addPackage(pack);
                DeliveryDateWriter ddw = new DeliveryDateWriter() ;
                ddw.insertText(box.toString());
                return box.getCode();
            }
        }
        return 0;
    }

    public boolean removePackage(Package pack) throws IOException {
        for(Box box : boxList){
            if(box.getPack() == pack){
                DeliveryDateWriter ddw = new DeliveryDateWriter() ;
                ddw.removeText(box.getCode());
                box.removePackage();
                return true;
            }
        }
        return false;
    }


}
