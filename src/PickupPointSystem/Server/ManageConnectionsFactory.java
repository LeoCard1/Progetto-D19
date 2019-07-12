package PickupPointSystem.Server;


/**
 * @author Gruppo D19
 * @version 1.0.0
 */


public class ManageConnectionsFactory {

    /**
     * This method returns a connection
     * type based on a string.
     *
     * @param type This is the type of connection.
     * @return The connection.
     */

    public ManageConnections getConnection(String type) {
        if (type.equals("deliveryman")) return new ManageConnectionsDeliveryMan();
        return null;
    }
}
