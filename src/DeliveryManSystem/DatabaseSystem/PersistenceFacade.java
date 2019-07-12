package DeliveryManSystem.DatabaseSystem;



import DeliveryManSystem.DatabaseSystem.Mappers.*;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryManTable;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;

import java.io.IOException;
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

    public ArrayList<DeliveryTable> getDeliveries(String delID) throws IOException {
        return (ArrayList<DeliveryTable>) getMapper("DeliveryMapper").get(delID);
    }

    /**
     * This method returns the DeliveryManTable identified by the given id.
     * @param delID
     * @return DeliveryManTable
     */

    public DeliveryManTable getDeliveryMan(String delID) throws IOException {
        return (DeliveryManTable) getMapper("DeliveryManMapper").get(delID);
    }


    /**
     * This method returns the PickupPointTable identified by the given id.
     * @param piPoID
     * @return PickupPointTable
     */

    public PickupPointTable getPickupPoint(String piPoID) throws IOException {
        return (PickupPointTable) getMapper("PickupPointMapper").get(piPoID);
    }

    /**
     * This method returns a mapper by the mapperName passed as an argument
     * @param mapperName the name of the requested mapper
     * @return the requested mapper
     */

    public Mapper getMapper(String mapperName){
        return mappers.get(mapperName);
    }
}

