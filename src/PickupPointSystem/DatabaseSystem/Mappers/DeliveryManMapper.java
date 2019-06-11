package PickupPointSystem.DatabaseSystem.Mappers;


import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;

/**
 * @author D19 Group
 * @version 2.0
 */

public class DeliveryManMapper implements Mapper {

    /**
     * This method returns the internal DeliveryMan to the database given the
     * id passed as an argument.
     * @param delID
     * @return DeliveryMan
     */

    public DeliveryMan get(String delID){
        MainServerConnector server = new MainServerConnector();

        String password = server.deliveryManGet(delID);
        server.close();

        password = password.replaceAll("\n", "");

        if (password.equalsIgnoreCase("null")) return null;
        return new DeliveryMan(delID, password);
    }

}
