package Management;

import DeliveryManSystem.DeliveryMan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ManagerConnect extends Thread {

    private Manager manager;
    private Socket client = null;
    private BufferedReader in = null;
    private PrintStream out = null;

    public ManagerConnect(Socket client, Manager manager) throws IOException {
        this.manager=manager;
        this.client = client;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);
        this.start();
    }

    /**
     *  run:
     *  - se il messaggio del client è "DeliveryMan" il server resta in attesa di ricevere il messaggio che
     *  indica la richiesta di quel client, 2 casi possibili:
     *  (1) il client invia "authentication": il server resta in attesa di ricevere id e password, e se esiste
     *  un deliveryman con quell id e password lo autentifica.
     *  (2) il client invia "updatelist": il server resta in attesa del DeliveryManID, una volta ricevuto invia
     *  al client la lista dei pacchi da consegnare, dopodichè cancella la lista dei  pacchi dal DeliveryMan
     *  della lista del Manager.
     *  - se il messaggio del client è "PickupPoint" il server resta in attesa di ricevere il messaggio che
     *  indica la richiesta di quel client, 3 casi possibili:
     *  (1) il client invia "packpickedup": il server resta in attesa dell' ID del pacco, poi rimuove il pacco
     *  dalla lista, la data di consegna dal file DeliveryDate e anche la password dalla lista delle password
     *  associate agli ID dei pacchi, poi chiude la connessione.
     *  (2) il client invia "packadded": il server resta in attesa del boxToString e della password della box,
     *  dopodichè aggiorna il file DeliveryDate e aggiunge password e ID del pacco all'Hashmap, poi chiude la
     *  connessione.
     *  (3) il client invia "deliverymancredentials": in tal caso il server resta in attesa delle credenziali
     *  del deliveryman, se esiste un deliveryman con quelle credenziali invia "authenticated", poi chiude la
     *  connessione.
     */

    public void run() {
        try {
            String clientType = readMessage();
            if (clientType.equals("DeliveryMan")) {
                isDeliveryMan();
            } else if (clientType.equals("PickupPoint")) {
                isPickupPoint();
            }
            disconnect();
            System.out.println("[3] Disconnected from the client: " + client.getInetAddress());
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void isDeliveryMan() throws IOException {
        String request = readMessage();
        if(request.equals("authentication")){
            authentication();
        } else if(request.equals("updatelist")) {
           sendList();
        }
    }

    public void authentication() throws IOException {
        String line = readMessage();

        if (line.equals("")) {
            send("notauthenticated");
            return;
        }

        String[] division = line.split("\t");

        if (manager.getDeliveryMan(division[0]).getPassword().equals(division[1])) {
            send("authenticated");
        } else {
            send("notauthenticated");
        }
    }

    public void sendList() throws IOException {
        DeliveryMan del = manager.getDeliveryMan(readMessage());
        send(del.packageListToString());
    }

    public void isPickupPoint() throws IOException {
        String request = readMessage();
        if(request.equals("packpickedup")){
            packPickedUp();
        } else if(request.equals("packadded")){
           packAdded();
        } else if(request.equals("deliverymancredentials")){
            authentication();
        }
    }

    public void packPickedUp() throws IOException {
        String packID = readMessage();
        manager.removePackage(packID);
        manager.removeDeliveryDate(packID);
        manager.removePassword(packID);
    }

    public void packAdded() throws IOException {
        String boxToString = readMessage();
        String[] division = boxToString.split("\t");
        String packID = division[1];
        if(manager.getDeliveryManFromPackID(packID)!=null){
            manager.getDeliveryManFromPackID(packID).removePackage(packID);
        }
        manager.addDeliveryDate(boxToString);
        String password = readMessage();
        manager.addPassword(packID,password);
        //bisogna inviare la notifica all utente
    }

    public void send(String text){
        out.print(text +"\n");
    }

    public String readMessage() throws IOException {
        while(!in.ready()){

        }
        return in.readLine();
    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        client.close();
    }
}
