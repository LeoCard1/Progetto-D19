package PickupPointSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class PickupPointClient {

    BufferedReader in = null;
    PrintStream out = null;
    Socket socket = null;

    /**
     *  -notifyOfPackageAdded: notifica il ManagerServer dell'aggiunta del pacco, invia il boxToString
     *  per far si che il Manager aggiorni il file DeliveryDate e invia la password della box contenente
     *  il pacco consegnato.
     *  -notifyOfPackagePickedUp: notifica il ManagerServer del ritiro del pacco, invia l'id del pacco
     *  ritirato per far si che il Manager aggiorni le sue liste associate a quel pacco.
     *  -checkDeliveryManCredentials: invia al ManagerServer le credenziali ricevute in input chiedendone
     *  l'autenticazione.
     */

    public void connect() throws IOException {
        socket = new Socket("localhost", 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
        send("PickupPoint");
    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public void notifyOfPackageAdded(String boxToString, String password) throws IOException {
        connect();
        send("packadded");
        send(boxToString);
        send(password);
        disconnect();
    }

    public void notifyOfPackagePickedUp(String packID) throws IOException {
        connect();
        send("packpickedup");
        send(packID);
        disconnect();
    }

    public boolean checkDeliveryManCredentials(String credentials) throws IOException {
        boolean authenticated = false;
        connect();
        send("deliverymancredentials");
        send(credentials);
        String response = readLine();
        if(response.equals("authenticated")){
            authenticated = true;
        }
        disconnect();
        return authenticated;
    }

    public void send(String text) throws IOException {
        out.print(text +"\n");
    }

    public String readLine() throws IOException {
        while(!in.ready()){

        }
        return in.readLine();
    }
}
