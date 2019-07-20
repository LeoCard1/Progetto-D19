package ServerAndDatabase.Connections;

/**
 * This class returns a 'Connections'
 * class depending on the string received
 * by the server
 *
 * @author Gruppo D19
 * @version 1.0.1
 */

public class ConnectionsFactory {

    private ConnectionsDeliveryMan connectionsDeliveryMan = new ConnectionsDeliveryMan();
    private ConnectionsDelivery connectionsDelivery = new ConnectionsDelivery();
    private ConnectionsPackage connectionsPackage = new ConnectionsPackage();
    private ConnectionsPickupPoint connectionsPickupPoint = new ConnectionsPickupPoint();

    /**
     * This method returns a 'Connections'
     * class depending on the specified type
     *
     * @param type The type of 'Connections' class to return
     * @return The class that manages the connection
     */

    public Connection getConnection(String type) {
        if (type.equals("deliveryman")) return connectionsDeliveryMan;
        if (type.equals("delivery")) return connectionsDelivery;
        if (type.equals("package")) return connectionsPackage;
        if (type.equals("pickuppoint")) return connectionsPickupPoint;
        throw new ConnectionUnknownException();
    }
}
