package DeliveryManSystem.Client;

import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;
import DeliveryManSystem.ObserverResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class DeliveryManClient {
    private Socket client;
    private BufferedReader in;
    private PrintStream out;


    public void connectToPickupPoint(String ip, String id, String password) {
        try {
            client = new Socket();
            client.connect(new InetSocketAddress(ip, 8000));

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);

            goConnect(id, password);

        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: can't connect to the pickup point.");
        }
    }

    private void goConnect(String id, String password) throws IOException {
        System.out.println("Connection estabilished!");

        out.println("deliveryman");
        out.println("login " + id + " " + password);

        out.println("close");

        in.close();
        out.close();

        client.close();
    }

}
