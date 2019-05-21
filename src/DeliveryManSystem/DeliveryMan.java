package DeliveryManSystem;

import LockerSystem.Package;
import ObserverPattern.Observer;

import java.util.ArrayList;

public class DeliveryMan implements Observer {

    private String id;
    private String password;
    private ArrayList<Package> packageList = new ArrayList<>();
    private int packCounter;

    public DeliveryMan(String id, String password){
        this.password=password;
        this.id = id;
        packCounter = 0;
    }

    public String getId(){
        return id;
    }

    public String getPassword(){
        return password;
    }

    public void addPackage(Package pack){
        packageList.add(pack);
        packCounter++;
    }

    public void removePackage(Package pack){
        packageList.remove(pack);
    }

    public int getPackCounter() {
        return packCounter;
    }

    public ArrayList<Package> getPackageList() {
        return packageList;
    }

    public boolean hasPackage(String packID){
        for(Package pack : packageList){
            if(pack.getId().equals(packID)){
                return true;
            }
        }
        return false;

    }

    public String  packageListToString(){
        String text="";
        for(Package pack : packageList){
            text += pack.toString()+"\n";
        }
        return text;
    }

    @Override
    public void update() {
        packCounter += packageList.size();
        packageList.clear();
    }
}
