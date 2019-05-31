package NewClientServer;

import NewClientServer.Client.DeliveryManClient;
import NewClientServer.Server.PickupPointServer;

public class Main {
    public static void main(String[] args) {
        Thread server = new PickupPointServer();
        server.start();

        DeliveryManClient del = new DeliveryManClient();
        del.connectToPickupPoint();
    }
}
