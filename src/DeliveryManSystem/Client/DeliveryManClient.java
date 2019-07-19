package DeliveryManSystem.Client;

import DeliveryManSystem.DatabaseSystem.PersistenceFacade;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryManTable;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;

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
    private PickupPointTable pickupPointTable;

    public void connectToPickupPoint(PickupPointTable pickupPointTable, String id, String password) throws IOException {
        this.pickupPointTable = pickupPointTable;

        client = new Socket();
        client.connect(new InetSocketAddress(pickupPointTable.getIp(), 8000));

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);

        goConnect(id, password);
    }

    private void goConnect(String id, String password) throws IOException {
        System.out.println("Connection estabilished!");

        out.println("deliveryman");
        out.println("login " + id + " " + password);

        while(!in.ready());

        out.println("close");

        in.close();
        out.close();

        client.close();
    }

}
