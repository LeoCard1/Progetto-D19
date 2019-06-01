package PickupPointSystem.Server;

public class ManageConnectionsFactory {

    public ManageConnections getConnection(String type) {
        if (type.equals("deliveryman")) return new ManageConnectionsDeliveryMan();
        return null;
    }
}
