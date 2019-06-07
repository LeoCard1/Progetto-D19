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

    public MainServerConnector() {
        startServer();
    }

    private void startServer() {
        try {
            System.out.println("[0] Connecting to main server...");
            client.connect(new InetSocketAddress("127.0.0.1", 8500));

            System.out.println("[1] Connection successful!");
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String piPoID) {
        try {
            out.println("pickuppoint get " + piPoID);

            while (!in.ready()) ;
            StringBuffer strBuf = new StringBuffer();

            while (in.ready()) {
                strBuf.append(in.readLine() + "\n");
            }

            return strBuf.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void removeRowFromPackID(String packID) {
        out.println("pickuppoint removerowfrompackid " + packID);
    }

    public void updatePickupPoint(String packID, String dateOfDelivery, String boxNumber, String boxPassword) {
        out.println("pickuppoint update " + packID + " " + dateOfDelivery +
                " " + boxNumber + " " + boxPassword);
    }

    public void close() {
        out.println("pickuppoint close");
    }
}
