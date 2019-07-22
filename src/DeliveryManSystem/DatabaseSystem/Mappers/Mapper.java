package DeliveryManSystem.DatabaseSystem.Mappers;

import java.io.IOException;

/**
 * @author Gruppo D19
 * @version 1.0.0
 */

public interface Mapper {

    /**
     * This method returns some kind of object given a key
     *
     * @param oid The key
     * @return The object
     */

    Object get(String oid) throws IOException;
}