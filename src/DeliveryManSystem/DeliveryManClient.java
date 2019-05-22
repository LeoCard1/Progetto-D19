package DeliveryManSystem;

import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;
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

    private BufferedReader in = null;
    private PrintStream out = null;
    private Socket socket = null;
    private DeliveryMan deliveryMan;
    private ArrayList<Observer> observers = new ArrayList<>();

    /*
     *  -logIn: si connette al ManagerServer e invia id e password per autenticarsi, in
     *  caso positivo viene creato un deliveryman con quell'id e password.
     *  -sendList: invia id e password del DeliveryMan al PickupPointServer, in caso di
     *  autenticazione riuscita, invia al PickupPointServer la lista di pacchi in possesso
     *  dal corriere,  riceve dal PickupPointServer l'id dei pacchi e i relativi codici
     *  delle box in cui inserirli, dopodichè notifica il DeliveryMan in modo da svuotare
     *  la lista dei pacchi.
     *  -updateList: si connette al ManagerServer per ricevere la lista dei pacchi.
     *  -addPackagesFromList: aggiunge i pacchi alla lista del DeliveryMan avendo in input un
     *  testo con packID e dimensioni.
     */

    public DeliveryManClient() {
        Gui gui = new Gui(this);
    }

    public boolean logIn(String id, String password) throws IOException {
         if (authenticationManager(id, password)) {
            deliveryMan = new DeliveryMan(id,password);
            addObserver(deliveryMan);
            return true;
         }  else {
            return false;
         }
    }

    private void connectPickupPoint() throws IOException {
        socket = new Socket("localhost", 8000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
    }

    private void connectManager() throws IOException {
        socket = new Socket("localhost", 5000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
        send("DeliveryMan");
    }

    public void sendList() throws IOException {
        if(authenticationPickupPoint()){
            System.out.println("Authenticated");
            send(deliveryMan.packageListToString());
            System.out.println(readMessage());
        } else {
            System.err.println("Not authenticated!");
        }
        disconnect();
        notifyObservers();
    }

    public void updateList() throws IOException {
        connectManager();
        send("updatelist");
        send(deliveryMan.getId());
        addPackagesFromList();
        System.out.println("Package list updated");
        disconnect();
    }

    public boolean authenticationManager(String id, String password) throws IOException {
        connectManager();
        send("authentication");
        send(id + "\t" + password);
        String response =  readLine();
        if (response.equals("authenticated")){
            return true;
        } else {
            return false;
        }
    }

    public boolean authenticationPickupPoint() throws IOException {
        connectPickupPoint();
        send(deliveryMan.getId() + "\t" + deliveryMan.getPassword());
        String response =  readLine();
        if (response.equals("authenticated")){
            return  true;
        }  else {
            return false;
        }
    }

    public void send(String text){
        out.print(text +"\n");
    }

    public String readMessage() throws IOException {
        String message = "";
        while(!in.ready()){

        }
        while(in.ready()){
            message+= in.readLine() +"\n";
        }
        return message;
    }

    public String readLine() throws IOException {
        while(!in.ready()){

        }
        return in.readLine();
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

    public void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    private void addObserver(Observer ob){
        observers.add(ob);
    }

    private void notifyObservers(){
        for(Observer ob : observers){
            ob.update();
        }
    }
}
