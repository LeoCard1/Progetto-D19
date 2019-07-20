package ServerAndDatabase.Connections;

public class ConnectionsFactory {

    private ConnectionsDeliveryMan connectionsDeliveryMan = new ConnectionsDeliveryMan();
    private ConnectionsDelivery connectionsDelivery = new ConnectionsDelivery();
    private ConnectionsPackage connectionsPackage = new ConnectionsPackage();
    private ConnectionsPickupPoint connectionsPickupPoint = new ConnectionsPickupPoint();

    public Connection getConnection(String type) {
        if (type.equals("deliveryman")) return connectionsDeliveryMan;
        if (type.equals("delivery")) return connectionsDelivery;
        if (type.equals("package")) return connectionsPackage;
        if (type.equals("pickuppoint")) return connectionsPickupPoint;
        throw new ConnectionUnknownException();
    }
}
