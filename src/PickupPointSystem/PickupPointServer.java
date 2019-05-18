package PickupPointSystem;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PickupPointServer extends Thread{

    private ServerSocket server;
    private PickupPoint pickupPoint;

    public PickupPointServer(PickupPoint pickupPoint) throws IOException {
        this.pickupPoint = pickupPoint;
        server = new ServerSocket(8000);
        System.out.println("[0] PickupPointServer waiting on port 8000...");
        this.start();
    }

    /*
     *  -run: il server una volta accettata una connessione da un client crea la classe PickupPointConnect
     *  con quel determinato client, la classe PickupPointConnect risponderà alle richieste di quel client
     *  il server non puo ricevere connessioni da parte di piu client contemporaneamente.
     */

    public void run() {
        while(true) {
            try {
                System.out.println("[1] Waiting for connection...");
                Socket client = server.accept();
                System.out.println("[2] Connection accepted by: " + client.getInetAddress());
                PickupPointConnect connect = new PickupPointConnect(client, pickupPoint);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
