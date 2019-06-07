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

public class MainServer extends Thread {
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintStream out;

    @Override
    public void run() {
        try {
            server = new ServerSocket(8500);
            System.out.println("[0] PickupPointServer waiting on port 8500...");
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void handleConnection() throws IOException {
        while (!client.isClosed()) {
            while (!in.ready()) ;

            StringTokenizer strTok = new StringTokenizer(in.readLine());
            ConnectionsFactory conFac = new ConnectionsFactory();

            try {

                Connection connection = conFac.getConnection(strTok.nextToken());
                if (!connection.manageConnection(in, out, client, strTok)) break;

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
