package PickupPointSystem;

import GraphicalInterface.GraIntMain;
import LockerSystem.BoxType.*;
import LockerSystem.Package;

import LockerSystem.Size;
import Management.CodeGenerator;
import Management.FileManager.DeliveryDateWriter;
import Management.FileManager.PackagesListReader;
import ObserverPattern.Observer;

import java.io.IOException;
import java.util.*;


public class PickupPoint {
    private String id;
    private ArrayList<Box> boxList = new ArrayList<>();
    private HashMap<String, Box> availableBox = new HashMap<>();
    private ArrayList<Observer> obsList = new ArrayList<>();


    public PickupPoint(String id, int numSmallBox, int numMediumBox, int numLargeBox) {
        this.id = id;
        for(int i = 0; i < numSmallBox; i++){
            boxList.add(new SmallBox());
        }
        for(int i = 0; i < numMediumBox; i++){
            boxList.add(new MediumBox());
        }
        for(int i = 0; i < numLargeBox; i++){
            boxList.add(new LargeBox());
        }
        createGUI();
    }

    public ArrayList<Box> getBoxList() {
        return boxList;
    }


    public int addPackage(Package pack) throws IOException {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize()) > -1){
                box.addPackage(pack);
                DeliveryDateWriter ddw = new DeliveryDateWriter() ;
                ddw.insertText(box.toString());
                CodeGenerator generator = new CodeGenerator();
                String password = generator.generateBoxPassword(box.toString());
                availableBox.put(password, box);
                notifyObservers();
                return box.getCode();
            }
        }
        return 0;
    }

    public void emptyBox(String cod) throws IOException {
        Box box = null;
        Package pack = null;
        try {
            box = availableBox.get(cod);
            pack = box.getPack();
            PackagesListReader plr = new PackagesListReader();
            plr.removeText(pack.getId());
            DeliveryDateWriter ddw = new DeliveryDateWriter() ;
            ddw.removeText(box.getCode());
            box.removePackage();
            availableBox.remove(cod);
            notifyObservers();
        }
        catch (NullPointerException e) {System.out.println("ERROR: The code is not valid.");}
    }


    private void createGUI() {
        GraIntMain gui = new GraIntMain(this);
        addObserver(gui);
    }

    private void addObserver(Observer obs) {
        for (Observer obsOfList : obsList) {
            if (obs == obsOfList) return;
        }

        obsList.add(obs);
    }

    private void notifyObservers(){
        for (Observer obsOfList : obsList) {
            obsOfList.update();
        }
    }

    public Box getBoxFromIndex(int index) {
        return boxList.get(index);
    }

    public Size getBoxSizeGivenIndex(int index) {
        return getBoxFromIndex(index).getSize();
    }
}
