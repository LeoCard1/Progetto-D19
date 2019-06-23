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
    private ArrayList<ObserverResponse> obsList = new ArrayList<>();


    public void connectToPickupPoint(String id, String password) {
        try {
            client = new Socket();
            client.connect(new InetSocketAddress("localhost", 8000));

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

        while (!in.ready()) ;
        String response = in.readLine();
        notifyObservers(response);

        out.println("close");

        in.close();
        out.close();

        client.close();
    }

    public void addObserver(ObserverResponse obs) {
        for (ObserverResponse obsInList : obsList) {
            if (obsInList.equals(obs)) return;
        }

        obsList.add(obs);
    }

    public void notifyObservers(String result) {
        for (ObserverResponse obs : obsList) {
            obs.update(result);
        }
    }
}
