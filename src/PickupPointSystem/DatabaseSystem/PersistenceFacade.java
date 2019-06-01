package PickupPointSystem.DatabaseSystem;


import PickupPointSystem.DatabaseSystem.Mappers.DeliveryMapper;
import PickupPointSystem.DatabaseSystem.Mappers.Mapper;
import PickupPointSystem.DatabaseSystem.Mappers.MapperFactory;
import PickupPointSystem.DatabaseSystem.Tables.Delivery;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;

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
     * This method returns an ArrayList of packages inside the database given the DeliveryMan
     * id passed as an argument.
     * @param delID
     * @return ArrayList<Package>
     */

    public ArrayList<Package> getPackagesFromDelID(String delID){
        ArrayList<Delivery> deliveries = ((DeliveryMapper) mappers.get("DeliveryMapper")).get(delID);
        ArrayList<Package> packages = new ArrayList<>();
        for(Delivery delivery : deliveries) {
            Package pack = (Package) mappers.get("PackageMapper").get(delivery.getPackID());
            packages.add(pack);
        }
        return packages;
    }

    /**
     * This method returns the internal DeliveryMan to the database given the DeliveryMan id.
     * @param delID
     * @return DeliveryMan
     */

    public DeliveryMan getDeliveryMan(String delID){
        DeliveryMan deliveryMan = (DeliveryMan) mappers.get("DeliveryManMapper").get(delID);
        return deliveryMan;
    }





}
