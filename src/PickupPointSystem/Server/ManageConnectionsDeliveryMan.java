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
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintStream out;
    private PersistenceFacade facade = new PersistenceFacade();

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

        if (line.equals("close")) {
            out.close();
            client.close();
            in.close();
            return;
        }

        StringTokenizer st = new StringTokenizer(line);
        String delID = st.nextToken();
        String password = st.nextToken();

        if(authenticationDeliveryMan(delID,password)){
            addDeliverymanPackages(delID);
        }
    }

    private boolean authenticationDeliveryMan(String delID, String password){
        DeliveryMan del = facade.getDeliveryMan(delID);
        if(del!=null && del.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    private String addDeliverymanPackages(String delID) throws IOException {
        PickupPoint pickupPoint = PickupPoint.getInstance("PAV01",21,15,9);
        ArrayList<Package> packages = facade.getPackagesFromDelID(pickupPoint.getId(), delID);
        String message ="";
        for(Package pack : packages){
            int boxNumber = pickupPoint.addPackage(pack);
            message+= pack.getId() + "\t" + boxNumber+"\n";
        }
        return message;
    }

}
