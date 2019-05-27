package Management;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerServer extends Thread{

    private Manager manager;
    private ServerSocket server;

    public ManagerServer(Manager manager) throws IOException {
        this.manager=manager;
        server = new ServerSocket(5000);
        System.out.println("[0] ManagerServer waiting on port 5000...");
    }

    /**
     *  -run: il server una volta accettata una connessione da un client crea la classe ManagerConnect
     *  con quel determinato client, la classe ManagerConnect risponderà alle richieste di quel client
     *  nel frattempo il server resta in attesa di ulteriori connessioni da parte di altri client.
     */

    public void run() {
        while(true) {
            try {
                System.out.println("[1] Waiting for connection...");
                Socket client = server.accept();
                System.out.println("[2] Connection accepted by: " + client.getInetAddress());
                ManagerConnect c = new ManagerConnect(client, manager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
