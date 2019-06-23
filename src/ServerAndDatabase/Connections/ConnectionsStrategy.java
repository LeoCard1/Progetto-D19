package ServerAndDatabase.Connections;

public class ConnectionsStrategy {

    private ConnectionsDeliveryMan connectionsDeliveryMan = new ConnectionsDeliveryMan();
    private ConnectionsDelivery connectionsDelivery = new ConnectionsDelivery();
    private ConnectionsPackage connectionsPackage = new ConnectionsPackage();
    private ConnectionsPickupPoint connectionsPickupPoint = new ConnectionsPickupPoint();

    public Connection getConnection(String type) {
        if (type.equalsIgnoreCase("deliveryman")) return connectionsDeliveryMan;
        if (type.equalsIgnoreCase("delivery")) return connectionsDelivery;
        if (type.equalsIgnoreCase("package")) return connectionsPackage;
        if (type.equalsIgnoreCase("pickuppoint")) return connectionsPickupPoint;
        throw new ConnectionUnknownException();
    }
}
