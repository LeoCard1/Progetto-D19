package PickupPointSystem.DatabaseSystem.Mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.StringTokenizer;

public class MainServerConnector {
    static MainServerConnector instance;
    private Socket client = new Socket();
    private BufferedReader in;
    private PrintStream out;

    private MainServerConnector() {
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

            System.out.println(strBuf);
            return strBuf.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void close() {
        out.println("pickuppoint close");
        instance = null;
    }

    public static synchronized MainServerConnector getInstance() {
        if (instance == null) instance = new MainServerConnector();
        return instance;
    }
}
