package DeliveryManSystem;

import LockerSystem.Package;
import ObserverPattern.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DeliveryManClient {

    BufferedReader in = null;
    PrintStream out = null;
    Socket socket = null;
    DeliveryMan deliveryMan;
    private ArrayList<Observer> observers = new ArrayList<>();

    public DeliveryManClient(String id){
        deliveryMan= new DeliveryMan(id);
        addObserver(deliveryMan);
    }

    /*
     *  -sendList: invia al PickupPointServer la lista di pacchi in possesso dal corriere,
     *  riceve dal PickupPointServer l'id dei pacchi e i relativi codici delle box in cui
     *  inserirli, dopodichè notifica il DeliveryMan in modo da svuotare la lista dei pacchi.
     *  -updateList: si connette al ManagerServer per ricevere la lista dei pacchi.
     *  -addPackagesFromList: aggiunge i pacchi alla lista del DeliveryMan avendo in input un
     *  testo con packID e dimensioni.
     */

    public void sendList() throws IOException {
        socket = new Socket("localhost", 8000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
        send(deliveryMan.packageListToString());
        readMessage();
        in.close();
        out.close();
        socket.close();
        notifyObservers();
    }

    public void updateList() throws IOException {
        socket = new Socket("localhost", 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
        send("DeliveryMan");
        send(deliveryMan.getId());
        addPackagesFromList();
        in.close();
        out.close();
        socket.close();
    }

    public void addPackagesFromList() throws IOException {
        while(!in.ready()){

        }
        while(in.ready()){
            StringTokenizer st = new StringTokenizer(in.readLine());
            while(st.hasMoreTokens()) {
                String id = st.nextToken();
                deliveryMan.addPackage(new Package(id, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
            }
        }
    }
    
    public void send(String text){
        out.print(text +"\n");
    }

    public void readMessage() throws IOException {
        String message = "";
        while(!in.ready()){
            
        }
        while(in.ready()){
            message+= in.readLine();
        }
        System.out.println(message);
    }

    public void addObserver(Observer ob){
        observers.add(ob);
    }

    public void notifyObservers(){
        for(Observer ob : observers){
            ob.update();
        }
    }
}
