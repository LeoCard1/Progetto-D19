package PickupPointSystem.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ManageConnectionsDeliveryMan implements ManageConnections {
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintStream out;

    @Override
    public void goConnect(ServerSocket server, Socket client, BufferedReader in, PrintStream out) throws IOException {
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

        if (line.equals("close")) {
            out.close();
            client.close();

            in.close();

            return;
        }

        System.out.println(line);
    }
}
