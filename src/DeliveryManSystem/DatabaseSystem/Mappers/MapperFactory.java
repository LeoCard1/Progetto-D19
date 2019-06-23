package DeliveryManSystem.DatabaseSystem.Mappers;


import java.util.HashMap;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class MapperFactory {

    /**
     * This method return a HashMap containing all types of mappers associated
     * with strings.
     * @return HashMap<String, Mapper>
     */

    public HashMap<String, Mapper> getAllMappers() {
        HashMap<String, Mapper> mappers = new HashMap<>();
        mappers.put("DeliveryMapper", new DeliveryMapper());
        mappers.put("DeliveryManMapper",  new DeliveryManMapper());
        mappers.put("PickupPointMapper", new PickupPointMapper());
        return mappers;
    }
}
