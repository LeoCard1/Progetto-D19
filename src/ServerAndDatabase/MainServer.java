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
    private ConnectionsFactory conStr;

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
