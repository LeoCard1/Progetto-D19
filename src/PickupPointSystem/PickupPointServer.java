package PickupPointSystem;


import LockerSystem.Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class PickupPointServer extends Thread{

    private ServerSocket server;
    private PickupPoint pickupPoint;

    public PickupPointServer(PickupPoint pickupPoint) throws IOException {
        this.pickupPoint = pickupPoint;
        server = new ServerSocket(8000);
        System.out.println("[0] PickupPointServer waiting on port 8000...");
        this.start();
    }

    /*
     *  -connect: legge la lista di package inviatagli dal DeliveryManClient richiamando quindi
     *  l'addPackage del PickupPoint, restituisce al DeliveryManClient l'id del pacco e il
     *  codice della box in cui inserirlo. Dopodichè si disconnette dal client.
     */

    public void run() {
        while(true) {
            try {
                System.out.println("[1] Waiting for connection...");
                Socket client = server.accept();
                System.out.println("[2] Connection accepted by: " + client.getInetAddress());
                connect(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void connect(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintStream out = new PrintStream(client.getOutputStream(), true);
        String message = "";
        while(!in.ready()){

        }
            String credentials = in.readLine();
            if(pickupPoint.checkDeliveryManCredentials(credentials)) {
                out.print("authenticated\n");
                while(!in.ready()){

                }
                while(in.ready()) {
                    StringTokenizer st = new StringTokenizer(in.readLine());
                    while (st.hasMoreTokens()) {
                        String id = st.nextToken();
                        int boxCode = pickupPoint.addPackage(new Package(id, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
                        message += "Pack: " + id + " Box number: " + boxCode + "\t";
                    }
                }
            } else {
                out.print("notauthenticated\n");
                System.err.println("Not authenticated") ;
            }

        out.print(message +"\n");
        in.close();
        out.close();
        client.close();
        System.out.println("[3] Disconnected from the client: " + client.getInetAddress());
    }


}
