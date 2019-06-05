package PickupPointSystem.Server;

import PickupPointSystem.DatabaseSystem.Tables.Package;
import PickupPointSystem.DatabaseSystem.PersistenceFacade;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;
import PickupPointSystem.PickupPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ManageConnectionsDeliveryMan implements ManageConnections {
    private PickupPoint pickupPoint;
    private PersistenceFacade facade = new PersistenceFacade();
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintStream out;

    @Override
    public void goConnect(PickupPoint pickupPoint, ServerSocket server, Socket client, BufferedReader in, PrintStream out) throws IOException {
        this.pickupPoint = pickupPoint;
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

            System.out.println(delID + " " + password);

            if (authenticationDeliveryMan(delID, password)) {
                out.println("accepted");
                addDeliverymanPackages(delID);
            } else out.println("refused");
        }
    }

    private boolean authenticationDeliveryMan(String delID, String password){
        DeliveryMan del = facade.getDeliveryMan(delID);
        if (del != null && del.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    private String addDeliverymanPackages(String delID) throws IOException {
        ArrayList<Package> packages = facade.getPackagesFromDelID(pickupPoint.getId(), delID);
        String message ="";
        for(Package pack : packages){
            int boxNumber = pickupPoint.addPackage(pack);
            message+= pack.getId() + "\t" + boxNumber+"\n";
        }
        
        return message;
    }

}
