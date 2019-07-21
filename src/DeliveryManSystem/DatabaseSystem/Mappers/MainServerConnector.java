package DeliveryManSystem.DatabaseSystem.Mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * This class allows the connection between the pickup
 * point and the main server which connects to the database
 *
 * @author Gruppo D19
 * @version 1.0.3
 */

public class MainServerConnector {
    private Socket client = new Socket();
    private BufferedReader in;
    private PrintStream out;

    /**
     * The constructor
     * @throws IOException Input/Output error between client and server
     */

    public MainServerConnector() throws IOException {
        startServer();
    }

    /**
     * This method starts the client, which then connects
     * to the main server
     *
     * @throws IOException Input/Output Error between client and server
     */

    private void startServer() throws IOException {
        System.out.println("[0] Connecting to main server...");
        client.connect(new InetSocketAddress("127.0.0.1", 8600));
        System.out.println("[1] Connection successful!");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);
    }

    /**
     * This method gets the delivery data of a specific pickup point
     * from the main server given the deliveryman's ID
     *
     * @param delID The deliveryman's ID
     * @return The delivery data received from the server
     * @throws IOException Input/Output error between client and server
     */

    public String getDelivery(String delID) throws IOException {
        return  sendAndWaitForResponse("delivery getfromdelid " + delID);
    }

    /**
     * This method gets the deliveryman's password given his ID from
     * the database
     *
     * @param delID The deliveryman's ID
     * @return The password
     * @throws IOException Input/Output error between client and server
     */

    public String getDeliveryMan(String delID) throws IOException {
        return sendAndWaitForResponse("deliveryman get " + delID);
    }

    /**
     * This method gets the pickup point data from its ID
     *
     * @param piPoID The pickup point ID
     * @return The pickup point data
     * @throws IOException Input/Output error between client and server
     */

    public String getPickupPoint(String piPoID) throws IOException {
        return sendAndWaitForResponse("pickuppoint get " + piPoID);
    }

    /**
     * This method signals to the server to close the connection
     */

    public void close() {
        out.println("close");
    }

    /**
     * This method lets the client send a string and then wait for a
     * response from the server
     *
     * @param command The string that must be sent
     * @return The received string
     * @throws IOException Input/Output error between client and server
     */

    private String sendAndWaitForResponse(String command) throws IOException {
        StringBuffer strBuf = new StringBuffer();
        out.println(command);

        while (!in.ready()) ;

        while (in.ready()) {
            strBuf.append(in.readLine() + "\n");
        }

        return strBuf.toString();
    }
}
