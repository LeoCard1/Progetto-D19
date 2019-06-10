package ServerAndDatabase.Connections;

public class ConnectionsFactory {

    public Connection getConnection(String type) {
        if (type.equalsIgnoreCase("deliveryman")) return new ConnectionsDeliveryMan();
        if (type.equalsIgnoreCase("pickuppoint")) return new ConnectionsPickupPoint();
        if (type.equalsIgnoreCase("package")) return new ConnectionsPackage();
        throw new ConnectionUnknownException();
    }
}
