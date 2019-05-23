package PickupPointSystem;

import LockerSystem.Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class PickupPointConnect {

    private Socket client;
    private PickupPoint pickupPoint;
    private BufferedReader in = null;
    private PrintStream out = null;

    /**
     *  -connect: riceve id e password del DeliveryMan, quindi richiama il
     *  checkDeliveryManCredentials del PickupPoint, in caso di autenticazione, legge la lista
     *  di package inviatagli dal DeliveryManClient richiamando quindi l'addPackage del PickupPoint,
     *  restituisce al DeliveryManClient l'id del pacco e il codice della box in cui inserirlo.
     *  Dopodichè si disconnette dal client.
     */

    public PickupPointConnect(Socket client, PickupPoint pickupPoint) throws IOException {
        this.client = client;
        this.pickupPoint = pickupPoint;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);
        connect();
    }

    public void connect() throws IOException {
        StringBuilder message = new StringBuilder();
        String credentials = readLine();
        if (pickupPoint.checkDeliveryManCredentials(credentials)) {
            send("authenticated");
            System.out.println("Authenticated");
            while (!in.ready()) ;
            while (in.ready()) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                while (st.hasMoreTokens()) {
                    String id = st.nextToken();
                    int boxCode = pickupPoint.addPackage(new Package(id, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
                    message.append("Pack: ").append(id).append(" Box number: ").append(boxCode).append("\n");
                }
            }
        } else {
            send("notauthenticated");
            System.err.println("Not authenticated") ;
        }
        send(message.toString());
        disconnect();
        System.out.println("[3] Disconnected from the client: " + client.getInetAddress());
    }

    public void send(String text){
        out.print(text+"\n");
    }

    public String readLine() throws IOException {
        while (!in.ready()) ;
        return in.readLine();
    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        client.close();
    }
}
