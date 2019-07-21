package DeliveryManSystem.DatabaseSystem.Mappers;

import java.util.HashMap;

/**
 * This factory creates mapper instances
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class MapperFactory {

    /**
     * This method returns a HashMap containing
     * all the types of mappers
     *
     * @return HashMap<String, Mapper> The mappers
     */

    public HashMap<String, Mapper> getAllMappers() {
        HashMap<String, Mapper> mappers = new HashMap<>();
        mappers.put("DeliveryMapper", new DeliveryMapper());
        mappers.put("DeliveryManMapper",  new DeliveryManMapper());
        mappers.put("PickupPointMapper", new PickupPointMapper());
        return mappers;
    }
}
