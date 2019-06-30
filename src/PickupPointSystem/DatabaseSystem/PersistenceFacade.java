package PickupPointSystem.DatabaseSystem;


import PickupPointSystem.DatabaseSystem.Mappers.*;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryTable;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryManTable;
import PickupPointSystem.DatabaseSystem.Tables.PackageTable;
import PickupPointSystem.DatabaseSystem.Tables.PickupPointTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is an interface between the mappers and the other classes
 * that need to use them
 * @author Andrea Stella
 * @version 1.0
 */

public class PersistenceFacade {

    private HashMap<String, Mapper> mappers;

    /**
     * The constructor. Initializes the HashMap of mappers.
     */

    public PersistenceFacade(){
        MapperFactory mapperFactory = new MapperFactory();
        mappers = mapperFactory.getAllMappers();
    }

    /**
     * This method updated the database delivery
     * @param delivery the DeliveryTable to be updated
     */

    public void updateDelivery(DeliveryTable delivery) throws IOException {
        getDeliveryMapper().update(delivery);
    }

    /**
     * This method removes from the database the delivery associated with the pack id
     * passed as an argument
     * @param packID the Pack id
     */

    public void removeDelivery(String packID) throws IOException {
        getDeliveryMapper().removeRowFromPackID(packID);
    }

    /**
     * This method removes the pack from the database.
     * @param packID the id of Pack to be removed
     */

    public void removePack(String packID) throws IOException {
        getPackageMapper().remove(packID);
    }

    /**
     * This method returns the deliveries to be made in the Pickup Point specified by the
     * id passed as an argument
     * @param pipoID the Pickup Point id
     * @return the required ArrayList<DeliveryTable>
     */

    public ArrayList<DeliveryTable> getDeliveries(String pipoID) throws IOException {
        return getDeliveryMapper().get(pipoID);
    }

    /**
     * This method returns the DeliveryManTable identified by the given id.
     * @param delID the Delivery Man id
     * @return the required DeliveryManTable
     */

    public DeliveryManTable getDeliveryMan(String delID) throws IOException {
        return getDeliveryManMapper().get(delID);
    }

    /**
     * This method returns the PackageTable identified by the given id.
     * @param packID the Pack id
     * @return the required PackageTable
     */

    public PackageTable getPackage(String packID) throws IOException {
        return getPackageMapper().get(packID);
    }

    /**
     * This method returns the PickupPointTable identified by the given id.
     * @param piPoID the Pickup Point id
     * @return the required PickupPointTable
     */

    public PickupPointTable getPickupPoint(String piPoID) throws IOException {
        return getPickupPointMapper().get(piPoID);
    }

    /**
     * @return the DeliveryMapper
     */

    private DeliveryMapper getDeliveryMapper(){
        return (DeliveryMapper) mappers.get("DeliveryMapper");
    }

    /**
     * @return the DeliveryManMapper
     */

    private DeliveryManMapper getDeliveryManMapper(){
        return (DeliveryManMapper) mappers.get("DeliveryManMapper");
    }

    /**
     * @return the PackageMapper
     */

    private PackageMapper getPackageMapper(){
        return (PackageMapper) mappers.get("PackageMapper");
    }

    /**
     * @return the PickupPointMapper
     */

    private PickupPointMapper getPickupPointMapper() {
        return (PickupPointMapper) mappers.get("PickupPointMapper");
    }
}
