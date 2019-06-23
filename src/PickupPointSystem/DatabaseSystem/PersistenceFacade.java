package PickupPointSystem.DatabaseSystem;


import PickupPointSystem.DatabaseSystem.Mappers.*;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryTable;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryManTable;
import PickupPointSystem.DatabaseSystem.Tables.PackageTable;
import PickupPointSystem.DatabaseSystem.Tables.PickupPointTable;

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
     * This method returns an ArrayList of packages that the DeliveryManTable must deliver to the
     * PickupPointTable specified by the id entered as an argument.
     * @param pipoID, delID
     * @return ArrayList<PackageTable>
     */
    
    public ArrayList<PackageTable> getPackagesFromDelID(String pipoID, String delID){
        ArrayList<DeliveryTable> deliveries = getDeliveries(pipoID);
        ArrayList<PackageTable> packages = new ArrayList<>();
        for(DeliveryTable delivery : deliveries) {
            if(!delivery.wasMade() && delivery.hasDelID(delID)) {
                packages.add(getPackage(delivery.getPackID()));
            }
        }
        return packages;
    }


    /**
     * This method returns the DeliveryTable related to the pack id passed as an argument.
     * @param pipoID
     * @param packID
     * @return DeliveryTable
     */

    public DeliveryTable getDeliveryFromPackID(String pipoID, String packID){
        ArrayList<DeliveryTable> deliveries = getDeliveries(pipoID);
        for(DeliveryTable delivery : deliveries){
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

    public void updateDelivery(DeliveryTable delivery){
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
     * This method returns deliveries to the PickupPointTable id given.
     * @param pipoID
     * @return  ArrayList<DeliveryTable>
     */

    public ArrayList<DeliveryTable> getDeliveries(String pipoID){
        return getDeliveryMapper().get(pipoID);
    }

    /**
     * This method returns the DeliveryManTable identified by the given id.
     * @param delID
     * @return DeliveryManTable
     */

    public DeliveryManTable getDeliveryMan(String delID){
        return getDeliveryManMapper().get(delID);
    }

    /**
     *
     * This method returns the PackageTable identified by the given id.
     * @param packID
     * @return PackageTable
     */
    public PackageTable getPackage(String packID){
        return getPackageMapper().get(packID);
    }

    /**
     * This method returns the PickupPointTable identified by the given id.
     * @param piPoID
     * @return PickupPointTable
     */

    public PickupPointTable getPickupPoint(String piPoID) {
        return getPickupPointMapper().get(piPoID);
    }

    /**
     * This methods return the mappers.
     * @return Mapper
     */

    private DeliveryMapper getDeliveryMapper(){
        return (DeliveryMapper) mappers.get("DeliveryMapper");
    }

    private DeliveryManMapper getDeliveryManMapper(){
        return (DeliveryManMapper) mappers.get("DeliveryManMapper");
    }

    private PackageMapper getPackageMapper(){
        return (PackageMapper) mappers.get("PackageMapper");
    }

    private PickupPointMapper getPickupPointMapper() {
        return (PickupPointMapper) mappers.get("PickupPointMapper");
    }
}
