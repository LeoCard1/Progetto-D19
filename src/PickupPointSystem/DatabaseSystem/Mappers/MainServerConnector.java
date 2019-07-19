package PickupPointSystem.DatabaseSystem.Mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 *
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
     * constructor body
     * @throws IOException Input/Output Error between client and server
     */

    public MainServerConnector() throws IOException {
        startClient();
    }

    /**
     * This method starts the client, which then connects
     * to the main server
     * @throws IOException Input/Output Error between client and server
     */
    private void startClient() throws IOException {

        System.out.println("[0] Connecting to main server...");
        client.connect(new InetSocketAddress("127.0.0.1", 8600));

        System.out.println("[1] Connection successful!");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);

    }

    /**
     * this method gets the Delivery data of a specific pickup point
     * from the main server
     *
     * @param piPoID pickup point ID
     * @return received delivery string
     * @throws IOException Input/Output Error between client and server
     */

    public String getDelivery(String piPoID) throws IOException {
        return  sendAndWaitForResponse("delivery getfrompipoid " + piPoID);
    }

    /**
     * This method removes a row from the database
     * through a given package ID
     * @param packID The package ID
     */

    public void removeRowFromPackID(String packID) {
        out.println("delivery removerowfrompackid " + packID);
    }

    /**
     * This method updates the database
     *
     * @param packID The new package ID
     * @param dateOfDelivery The new date of delivery
     * @param boxNumber The new box number
     * @param boxPassword The new box password
     */

    public void updateDelivery(String packID, String dateOfDelivery, String boxNumber, String boxPassword) {
        out.println("delivery refresh " + packID + " " + dateOfDelivery +
                " " + boxNumber + " " + boxPassword);
    }

    /**
     * This method gets the deliveryman's password
     * from his ID
     *
     * @param delID The ID of the deliveryman
     * @return The password
     * @throws IOException Input/Output Error between client and server
     */

    public String deliveryManGet(String delID) throws IOException {

        return sendAndWaitForResponse("deliveryman get " + delID);

    }

    /**
     * This method gets the package data
     * from a package ID
     *
     * @param packageID The package ID
     * @return The package data
     * @throws IOException Input/Output Error between client and server
     */

    public String packageGet(String packageID) throws IOException {
        return sendAndWaitForResponse("package get " + packageID);
    }

    /**
     * This method removes a package from the database
     *
     * @param packID The package ID
     */

    public void removePackage(String packID) {
        out.println("package remove " + packID);
    }

    /**
     * This method gets the pickup point data
     * from its ID
     *
     * @param piPoID The pickup point ID
     * @return The pickup point data
     * @throws IOException Input/Output Error between client and server
     */

    public String pickupPointGet(String piPoID) throws IOException {
        return sendAndWaitForResponse("pickuppoint get " + piPoID);
    }

    /**
     * This method adds test packages to the
     * database. Used with JUnit
     */

    public void addTestPackages() {
        out.println("pickuppoint test");
    }

    /**
     * This method gets the test deliveryman's
     * password. Used with JUnit
     *
     * @return The test deliveryman's password
     * @throws IOException Input/Output Error between client and server
     */

    public String getTestPackageCode() throws IOException {
        return sendAndWaitForResponse("pickuppoint testpassword");
    }

    /**
     * This method signals to the server to
     * close the connection
     */

    public void close() {
        out.println("close");
    }

    /**
     * This method lets the client send a string
     * and then wait for a response by the server
     *
     * @param command The string that must be sent
     * @return The received string
     * @throws IOException Input/Output Error between client and server
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
