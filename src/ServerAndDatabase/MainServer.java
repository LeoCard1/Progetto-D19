package ServerAndDatabase;

import ServerAndDatabase.Connections.Connection;
import ServerAndDatabase.Connections.ConnectionUnknownException;
import ServerAndDatabase.Connections.ConnectionsFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * This thread runs the server which
 * accepts requests from pickup points
 * and deliverymen and responds accordingly
 *
 * @author Gruppo D19
 * @version 1.0.1
 */

public class MainServer extends Thread {
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintStream out;
    private ConnectionsFactory conStr;

    /**
     * This method starts the thread. It
     * also signals that the server has
     * started
     */

    @Override
    public void run() {
        try {
            server = new ServerSocket(8600);
            conStr = new ConnectionsFactory();
            System.out.println("[0] Server waiting on port " + server.getLocalPort()+ "...");
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method starts the server. It
     * listens and accepts connections
     *
     * @throws IOException
     */

    private void startServer() throws IOException {
        while (true) {
            System.out.println("[1] Waiting for connection...");

            client = server.accept();
            System.out.println("[2] Connection accepted from: " + client.getInetAddress());

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);

            handleConnection();
        }
    }

    /**
     * This method executes the requests
     * demanded by a client
     *
     * @throws IOException Input/Output error between client and server
     */

    private void handleConnection() throws IOException {
        String firstWord;

        while (!client.isClosed()) {
            while (!in.ready()) ;

            String stringReceived = in.readLine();
            System.out.println(stringReceived);

            StringTokenizer strTok = new StringTokenizer(stringReceived);

            try {

                firstWord = strTok.nextToken();

                if (firstWord.equals("close")){
                    closeConnection();
                    return;
                }

                Connection connection = conStr.getConnection(firstWord);
                connection.manageConnection(out, strTok);

            } catch (ConnectionUnknownException e) {

                e.printStackTrace();
                System.err.println("Client: " + client.getLocalAddress());
                closeConnection();

            } catch (SQLException e) {
                e.printStackTrace();
                closeConnection();
            }
        }
    }

    /**
     * This method closes the connection
     * between the server and a client
     */

    private void closeConnection() {
        try {
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
