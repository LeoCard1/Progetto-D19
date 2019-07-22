package PickupPointSystem.Server;

import PickupPointSystem.PickupPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Gruppo D19
 * @version 1.0.0
 */

public class PickupPointServer extends Thread {

    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintStream out;

    /**
     * Constructor.
     */

    public PickupPointServer(){

    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(8000);
            System.out.println("[0] PickupPointServer waiting on port " +server.getLocalPort()+"...");
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method initializes the server, which awaits a connection and manages it.
     * @throws IOException Exception caused by I/O operations.
     */

    private void startServer() throws IOException {
        while (true) {
            System.out.println("[1] Waiting for connection...");

            client = server.accept();
            System.out.println("[2] Connection accepted from: " + client.getInetAddress());

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);

            while (!in.ready()) ;

            String line = in.readLine();
            ManageConnections connection = new ManageConnectionsFactory().getConnection(line);

            connection.goConnect(client, in, out);
        }
    }
}
