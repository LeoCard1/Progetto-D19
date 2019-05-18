package Management;

import DeliveryManSystem.DeliveryMan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Connect extends Thread {

    private Manager manager;
    private Socket client = null;
    BufferedReader in = null;
    PrintStream out = null;
    String clientMessage ="";

    public Connect(Socket clientSocket, Manager manager) throws IOException {
        this.manager=manager;
        client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);
        this.start();
    }

    /*
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
                clientMessage = in.readLine();
                if(clientMessage.equals("DeliveryMan")){
                    clientMessage = in.readLine();
                    if(clientMessage.equals("authentication")){
                        String[] division = in.readLine().split("\t");
                        if(manager.getDeliveryMan(division[0])!=null && manager.getDeliveryMan(division[0]).getPassword().equals(division[1])){
                            out.print("authenticated\n");
                        } else {
                            out.print("notauthenticated\n");
                        }

                    } else if(clientMessage.equals("updatelist")) {
                        DeliveryMan del = manager.getDeliveryMan(in.readLine());
                        out.print(del.packageListToString() + "\n");
                        del.update();
                    }
                } else if(clientMessage.equals("PickupPoint")){
                    clientMessage = in.readLine();
                    if(clientMessage.equals("packpickedup")){
                        String packID = in.readLine();
                        manager.removePackage(manager.getPackage(packID));
                        manager.removeDeliveryDate(packID);
                        manager.removePassword(packID);
                    } else if(clientMessage.equals("packadded")){
                        String boxToString = in.readLine();
                        String[] division = boxToString.split(" ");
                        manager.addDeliveryDate(boxToString);
                        String password = in.readLine();
                        manager.addPassword(division[1],password);
                        //bisogna inviare la notifica all utente
                    } else if(clientMessage.equals("deliverymancredentials")){
                        String[] division = in.readLine().split("\t");
                        if(manager.getDeliveryMan(division[0])!=null && manager.getDeliveryMan(division[0]).getPassword().equals(division[1])){
                            out.print("authenticated\n");
                        } else {
                            out.print("notauthenticated\n");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        try {
            in.close();
            out.close();
            client.close();
            System.out.println("[3] Disconnected from the client: " + client.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
