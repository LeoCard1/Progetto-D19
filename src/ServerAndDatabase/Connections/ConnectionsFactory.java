package ServerAndDatabase.Connections;

public class ConnectionsFactory {

    public Connection getConnection(String type) {
        if (type.equalsIgnoreCase("deliveryman")) return new ConnectionsDeliveryMan();
        if (type.equalsIgnoreCase("delivery")) return new ConnectionsDelivery();
        if (type.equalsIgnoreCase("package")) return new ConnectionsPackage();
        if (type.equalsIgnoreCase("pickuppoint")) return new ConnectionsPickupPoint();
        throw new ConnectionUnknownException();
    }
}
