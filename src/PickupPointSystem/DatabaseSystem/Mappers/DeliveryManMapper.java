package PickupPointSystem.DatabaseSystem.Mappers;


import PickupPointSystem.DatabaseSystem.Tables.DeliveryManTable;

import java.io.IOException;

/**
 * This class returns DeliveryManTable
 * objects from the database
 *
 * @author Gruppo D19
 * @version 2.0.0
 */

public class DeliveryManMapper implements Mapper {

    /**
     * This method returns a DeliveryManTable
     * object from the database given the
     * deliveryman's ID
     *
     * @param delID The deliveryman's ID
     * @return The DeliveryManTable object
     * @throws IOException Input/Output error between client and server
     */

    public DeliveryManTable get(String delID) throws IOException {
        MainServerConnector server = new MainServerConnector();

        String password = server.deliveryManGet(delID);
        server.close();

        password = password.replaceAll("\n", "");

        if (password.equalsIgnoreCase("null")) return null;
        return new DeliveryManTable(delID, password);
    }
}
