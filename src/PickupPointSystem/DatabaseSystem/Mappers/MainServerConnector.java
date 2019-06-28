package PickupPointSystem.DatabaseSystem.Mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainServerConnector {
    private Socket client = new Socket();
    private BufferedReader in;
    private PrintStream out;

    public MainServerConnector() throws IOException {
        startServer();
    }

    private void startServer() throws IOException {

        System.out.println("[0] Connecting to main server...");
        client.connect(new InetSocketAddress("127.0.0.1", 9000));

        System.out.println("[1] Connection successful!");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);

    }

    public String getDelivery(String piPoID) throws IOException {
        return  sendAndWaitForResponse("delivery getfrompipoid " + piPoID);
    }

    public void removeRowFromPackID(String packID) {
        out.println("delivery removerowfrompackid " + packID);
    }

    public void updateDelivery(String packID, String dateOfDelivery, String boxNumber, String boxPassword) {
        out.println("delivery refresh " + packID + " " + dateOfDelivery +
                " " + boxNumber + " " + boxPassword);
    }

    public String deliveryManGet(String delID) throws IOException {

        return sendAndWaitForResponse("deliveryman get " + delID);

    }

    public String packageGet(String packageID) throws IOException {

        return sendAndWaitForResponse("package get " + packageID);

    }

    public void removePackage(String packID) {
        out.println("package remove " + packID);
    }

    public String pickupPointGet(String piPoID) throws IOException {

        return sendAndWaitForResponse("pickuppoint get " + piPoID);

    }

    public void close() {
        out.println("close");
    }

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
