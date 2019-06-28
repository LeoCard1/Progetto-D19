package DeliveryManSystem.DatabaseSystem.Mappers;


import DeliveryManSystem.DatabaseSystem.Tables.DeliveryManTable;

import java.io.IOException;

/**
 * @author D19 Group
 * @version 2.0
 */

public class DeliveryManMapper implements Mapper {

    /**
     * This method returns the internal DeliveryManTable to the database given the
     * id passed as an argument.
     * @param delID
     * @return DeliveryManTable
     */

    public DeliveryManTable get(String delID) throws IOException {
        MainServerConnector server = new MainServerConnector();

        String password = server.getDeliveryMan(delID);
        server.close();

        password = password.replaceAll("\n", "");

        if (password.equalsIgnoreCase("null")) return null;
        return new DeliveryManTable(delID, password);
    }

}
