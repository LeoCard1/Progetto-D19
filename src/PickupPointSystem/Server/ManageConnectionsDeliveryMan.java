package PickupPointSystem.Server;

import PickupPointSystem.CredentialsReceiver;
import PickupPointSystem.PickupPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

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
        StringTokenizer st = new StringTokenizer(line);

        String command = st.nextToken();

        if (command.equals("close")) {
            out.close();
            client.close();
            in.close();
            return;
        }
        if (command.equals("login")) {

            String delID = st.nextToken();
            String password = st.nextToken();
            CredentialsReceiver credentialReceiver = CredentialsReceiver.getInstance();
            credentialReceiver.receiveCredentials(delID, password);

        }
    }

}
