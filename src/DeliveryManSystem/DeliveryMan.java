package DeliveryManSystem;

import LockerSystem.Package;
import ObserverPattern.Observer;

import java.util.ArrayList;

/**
 * This class represent one delivery man
 * @author Roberto Zappa
 * @version 1.0
 */

public class DeliveryMan implements Observer {

    private String id;
    private String password;
    private ArrayList<Package> packageList = new ArrayList<>(40);

    public DeliveryMan(String id, String password){

        this.password=password;
        this.id = id;

    }

    /**
     * This method return the Id of one deliveryman
     * @return String
     */

    public String getId(){

        return id;
    }

    /**
     * This method return the password of one deliveryman
     * @return String
     */

    public String getPassword(){

        return password;
    }

    /**
     * This method add one pack into the deliveryman's array list
     * @param pack the pack that must be added
     */

    public void addPackage(Package pack){

        packageList.add(pack);

    }

    /**
     * This method search if one pack is into deliveryman's array list
     * @param packID the pack id of the pack that we are looking for
     * @return boolean
     */

    public boolean hasPackage(String packID){

        for(Package pack : packageList){
            if(pack.getId().equals(packID)){
                return true;
            }
        }
        return false;

    }

    /**
     * This method return all pack inside the deliveryman's array list like a one string.
     * The packs are separated each other with an underscore
     * @return String
     */

    public String  packageListToString(){

        String text="";
        for(Package pack : packageList){
            text += pack.toString()+"\n";
        }
        return text;

    }

    /**
     * This method wipe out all pack inside deliveryman's array list
     */

    public void update() {

        packageList.clear();

    }

}
