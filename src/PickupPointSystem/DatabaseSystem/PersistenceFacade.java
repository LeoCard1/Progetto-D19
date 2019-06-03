package PickupPointSystem.DatabaseSystem;


import PickupPointSystem.DatabaseSystem.Mappers.*;
import PickupPointSystem.DatabaseSystem.Tables.Delivery;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;
import PickupPointSystem.DatabaseSystem.Tables.Package;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class PersistenceFacade {

    private HashMap<String, Mapper> mappers;

    /**
     * The constructor. Initiatialize the HashMap of mappers.
     */

    public PersistenceFacade(){
        MapperFactory mapperFactory = new MapperFactory();
        mappers = mapperFactory.getAllMappers();
    }

    /**
     * This method returns an ArrayList of packages that the DeliveryMan must deliver to the
     * PickupPoint specified by the id entered as an argument.
     * @param pipoID, delID
     * @return ArrayList<Package>
     */
    
    public ArrayList<Package> getPackagesFromDelID(String pipoID, String delID){
        ArrayList<Delivery> deliveries = getDeliveries(pipoID);
        ArrayList<Package> packages = new ArrayList<>();
        for(Delivery delivery : deliveries) {
            if(!delivery.wasMade() && delivery.hasDelID(delID)) {
                packages.add(getPackage(delivery.getPackID()));
            }
        }
        return packages;
    }

    /**
     * This method returns the Delivery in which the pack was inserted in the box corresponding
     * to the id passed as an argument.
     * @param pipoID
     * @param boxNumber
     * @return Delivery
     */

    public Delivery getDeliveryFromBoxNumber(String pipoID, int boxNumber){
        ArrayList<Delivery> deliveries = getDeliveries(pipoID);
        for(Delivery delivery : deliveries){
            if(delivery.wasMade() && delivery.hasBoxNumber(boxNumber)){
                return delivery;
            }
        }
        return null;
    }

    /**
     * This method returns the Delivery related to the pack id passed as an argument.
     * @param pipoID
     * @param packID
     * @return Delivery
     */

    public Delivery getDeliveryFromPackID(String pipoID, String packID){
        ArrayList<Delivery> deliveries = getDeliveries(pipoID);
        for(Delivery delivery : deliveries){
            if(delivery.hasPackage(packID)){
                return delivery;
            }
        }
        return null;
    }

    /**
     * This method update the database delivery.
     * @param delivery
     */

    public void updateDelivery(Delivery delivery){
        getDeliveryMapper().update(delivery);
    }

    /**
     * This method removes the delivery from the database.
     * @param packID
     */

    public void removeDelivery(String packID){
        getDeliveryMapper().removeRowFromPackID(packID);
    }

    /**
     * This method removes the pack from the database.
     * @param packID
     */

    public void removePack(String packID){
        getPackageMapper().remove(packID);
    }

    /**
     * This method returns deliveries to the PickupPoint id given.
     * @param pipoID
     * @return  ArrayList<Delivery>
     */

    public ArrayList<Delivery> getDeliveries(String pipoID){
        return getDeliveryMapper().get(pipoID);
    }

    /**
     * This method returns the DeliveryMan identified by the given id.
     * @param delID
     * @return DeliveryMan
     */

    public DeliveryMan getDeliveryMan(String delID){
        return getDeliveryManMapper().get(delID);
    }

    /**
     *
     * This method returns the Package identified by the given id.
     * @param packID
     * @return Package
     */
    public Package getPackage(String packID){
        return getPackageMapper().get(packID);
    }

    /**
     * This methods return the mappers.
     * @return Mapper
     */

    public DeliveryMapper getDeliveryMapper(){
        return (DeliveryMapper) mappers.get("DeliveryMapper");
    }

    public DeliveryManMapper getDeliveryManMapper(){
        return (DeliveryManMapper) mappers.get("DeliveryManMapper");
    }

    public PackageMapper getPackageMapper(){
        return (PackageMapper) mappers.get("PackageMapper");
    }

}
