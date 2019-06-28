package DeliveryManSystem.DatabaseSystem;



import DeliveryManSystem.DatabaseSystem.Mappers.*;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryManTable;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;

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
     * This method returns deliveries of the delivery man specified by the id passed as
     * an argument
     * @param delID
     * @return  ArrayList<DeliveryTable>
     */

    public ArrayList<DeliveryTable> getDeliveries(String delID){
        return getDeliveryMapper().get(delID);
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

    private PickupPointMapper getPickupPointMapper() {
        return (PickupPointMapper) mappers.get("PickupPointMapper");
    }
}

