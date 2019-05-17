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
    PickupPoint pickupPoint;

    public PickupPointClient(PickupPoint pickupPoint){
        this.pickupPoint = pickupPoint;
    }
    public void connect() throws IOException {
        socket = new Socket("localhost", 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
        send("PickupPoint");
    }

    /*
     *  -notifyOfPackageAdded: notifica il ManagerServer dell'aggiunta del pacco, invia il boxToString
     *  per far si che il Manager aggiorni il file DeliveryDate e invia la password della box contenente
     *  il pacco consegnato.
     *  -notifyOfPackagePickedUp: notifica il ManagerServer del ritiro del pacco, invia l'id del pacco
     *  ritirato per far si che il Manager aggiorni le sue liste associate a quel pacco.
     *  -sendDeliveryManCode: invia al ManagerServer la password del DeliveryMan.
     */

    public void notifyOfPackageAdded(String boxToString, String password) throws IOException {
        connect();
        send("packadded");
        send(boxToString);
        send(password);
        in.close();
        out.close();
        socket.close();
    }

    public void notifyOfPackagePickedUp(String packID) throws IOException {
        connect();
        send("packpickedup");
        send(packID);
        in.close();
        out.close();
        socket.close();
    }

    public void sendDeliveryManCode() throws IOException {
        connect();
        send("deliverymancode");
        send(pickupPoint.getDeliveryManCode());
        in.close();
        out.close();
        socket.close();
    }

    public void send(String text) throws IOException {
        out.print(text +"\n");
    }
}
