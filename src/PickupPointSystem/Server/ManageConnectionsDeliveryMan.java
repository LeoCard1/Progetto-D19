package PickupPointSystem.Server;

import PickupPointSystem.LoginDelMan;
import PickupPointSystem.PickupPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class ManageConnectionsDeliveryMan implements ManageConnections {
    private PickupPoint pickupPoint;
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintStream out;

    @Override
    public void goConnect(PickupPoint pickupPoint, ServerSocket server, Socket client, BufferedReader in, PrintStream out) throws IOException {
        this.pickupPoint = pickupPoint;
        this.server = server;
        this.client = client;
        this.in = in;
        this.out = out;

        while (!client.isClosed()) {
            while (!in.ready()) ;
            manageConnection();
        }
    }

    private void manageConnection() throws IOException {
        String line = in.readLine();
        StringTokenizer st = new StringTokenizer(line);

        String command = st.nextToken();

        if (command.equals("close")) {
            out.close();
            client.close();
            in.close();
            return;
        }
        if (command.equals("login")) {
            LoginDelMan logDel = new LoginDelMan(pickupPoint);
            String delID = st.nextToken();
            String password = st.nextToken();

            System.out.println(delID + " " + password);

            if (logDel.login(delID, password)) {
                out.println("accepted");
                System.out.println(logDel.addDeliverymanPackages());
                System.out.println(logDel.getPackagesToPickup());
            } else out.println("refused");
        }
    }

}
