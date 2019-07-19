package PickupPointSystem.DatabaseSystem.Mappers;


import PickupPointSystem.DatabaseSystem.Tables.DeliveryManTable;

import java.io.IOException;

/**
 *
 * This method returns the internal DeliveryManTable to the database given the
 * id passed as an argument
 *
 * @author Gruppo D19
 * @version 2.0
 */

public class DeliveryManMapper implements Mapper {

    /**
     *
     * @param delID DeliveryMan ID
     * @return DeliveryManTable the table received by DB
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
