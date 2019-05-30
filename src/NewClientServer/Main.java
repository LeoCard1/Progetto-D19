package NewClientServer;

import NewClientServer.Server.PickupPointServer;

public class Main {
    public static void main(String[] args) {
        Thread server = new PickupPointServer();
        server.start();
    }
}
