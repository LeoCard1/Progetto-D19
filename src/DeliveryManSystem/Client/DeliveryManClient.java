package DeliveryManSystem.Client;

import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * This class connects the deliveryman
 * to the pickup point server
 *
 * @author Gruppo D19
 * @version 1.1.0
 */

public class DeliveryManClient {
    private Socket client;
    private BufferedReader in;
    private PrintStream out;

    /**
     * The constructor creates a socket
     * and tries to connect to the pickup
     * point server
     *
     * @param pickupPointTable The table containing the pickup point data
     * @param id The deliveryman's ID
     * @param password The deliveryman's password
     * @throws IOException Input/Output error between client and server
     */

    public void connectToPickupPoint(PickupPointTable pickupPointTable, String id, String password) throws IOException {

        client = new Socket();
        client.connect(new InetSocketAddress(pickupPointTable.getIp(), 8000));

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);

        goConnect(id, password);
    }

    /**
     * This method connects the client to
     * the pickup point server and attempts
     * to log in
     *
     * @param id The deliveryman's ID
     * @param password The deliveryman's password
     * @throws IOException Input/Output error between client and server
     */

    private void goConnect(String id, String password) throws IOException {
        System.out.println("Connection estabilished!");

        out.println("deliveryman");
        out.println("login " + id + " " + password);

        while(!in.ready());

        out.println("close");

        in.close();
        out.close();

        client.close();
    }
}
