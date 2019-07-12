package PickupPointSystem.Server;

import PickupPointSystem.CredentialsReceiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author Gruppo D19
 * @version 1.0.0
 */

public class ManageConnectionsDeliveryMan implements ManageConnections {


    private Socket client;
    private BufferedReader in;
    private PrintStream out;

    /**
     * This method reads the String sent by the client.
     *
     * @param client This is the client who is connected.
     * @param in Input Operation.
     * @param out Output Operation.
     * @throws IOException Exception caused by I/O operations.
     */

    @Override
    public void goConnect(Socket client, BufferedReader in, PrintStream out) throws IOException {
        this.client = client;
        this.in = in;
        this.out = out;

        while (!client.isClosed()) {
            while (!in.ready()) ;
            manageConnection();
        }
    }

    /**
     * This method manages the connection based on the string.
     *
     * @throws IOException Exception caused by I/O operations.
     */

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
