package Management;

import DeliveryManSystem.DeliveryMan;
import Management.FileManager.ReadWritePackagesList;
import Management.GraphicalInterfaceManager.ManagerFrame;
import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import LockerSystem.Package;
import Management.FileManager.ReadWriteDeliveryDate;
import ObserverPattern.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

public class Manager implements Observer {

    /**
     *  -deliveryMen: lista di DeliveryMan.
     *  -packages: lista di tutti i Package.
     *  -password: HashMap di packID associati a password per sbloccare la box in cui sono stati
     *  inseriti.
     *  -readWritePackagesList: reader e writer per il file PackageList.
     *  -readWriteDeliveryDate: reader e writer per il file DeliveryDate.
     */

    private ArrayList<DeliveryMan> deliveryMen = new ArrayList<>();
    private ArrayList<Package> packages = new ArrayList<>();
    private HashMap<String,String> password = new HashMap<>();
    private ReadWritePackagesList readWritePackagesList = new ReadWritePackagesList();
    private ReadWriteDeliveryDate readWriteDeliveryDate = new ReadWriteDeliveryDate();
    private ArrayList<Observer> observers = new ArrayList<>();


    public Manager() throws IOException {
        createServer();
        createGUI();
        readWritePackagesList.addObserver(this);
        updatePackages();
        notifyObserver();
    }

    /**
     *  -updatePackages: consente di aggiungere pacchi alla lista prendendo le
     *  informazioni dal file di testo PackagesList, se il pacco è gia presente non
     *  viene aggiunto.
     *  -removePackage: rimuove dal file PackagesList il pacco corrispondente all'id
     *  passato come argomento, quindi ReadWritePackagesList notifichera il manager del
     *  cambiamento che quindi effettuerà un update aggiornando l'arraylist packages.
     *  -addPassword: aggiunge all'HashMap ID del pacco e relativa password per sbloccare
     *  la box.
     *  -removePassword: rimuove dall'HashMap ID del pacco e relativa password dato l'ID
     *  in input.
     *  -addPackageToDeliveryMan: permette di aggiungere pacchi ad un determinato DeliveryMan
     *  dati ID del DeliveryMan e ID del pacco, è consentito solo se il pacco non è stato
     *  assegnato a nessun altro deliveryman.
     *  -getUnassignedPackagesList: ritorna i pacchi non ancora affidati ad un corriere,
     *  controlla se il pacco non è assegnato ad un corriere e se non è presente nell'
     *  HashMap password dei pacchi consegnati.
     *  -getInTransitPackagesList: ritorna i pacchi affidati ai corrieri non ancora consegnati.
     *  -addDeliveryDate: permette di aggiungere date di consegna al file DeliveryDate.
     *  -removeDeliveryDate: permette di rimuovere date di consegna al file DeliveryDate
     *  dato l'ID del pacco.
     *  -generateDeliveryManCode: genera password per DeliveryMan.
     *  -update: aggiorna la lista di Packages dopo un cambiamento nel file PackagesList.
     */

    public boolean createDeliveryMan(String id) {
        if(id.length()>=5) {
            if (getDeliveryMan(id) == null) {
                String password = generateDeliveryManCode();
                DeliveryMan del = new DeliveryMan(id, password);
                deliveryMen.add(del);
                notifyObserver();
                return true;
            } else {
                new ErrorGUIMain("Existing ID", false);
                return false;
            }
        } else {
            new ErrorGUIMain("ID too short!", false);
            return false;
        }
    }

    public void updatePackages() {
        try {
            String text = readWritePackagesList.getText();
            StringTokenizer st1 = new StringTokenizer(text, "\n");
            while(st1.hasMoreTokens()) {
                String riga = st1.nextToken();
                StringTokenizer st2 = new StringTokenizer(riga);
                String id = st2.nextToken();
                if(getPackage(id)==null) {
                    Package pack = new Package(id, Double.parseDouble(st2.nextToken()), Double.parseDouble(st2.nextToken()), Double.parseDouble(st2.nextToken()));
                    packages.add(pack);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new ErrorGUIMain(e.getMessage(), false);
        }
        notifyObserver();
    }

    public void removePackage(String packID) throws IOException {
        readWritePackagesList.removeText(packID);
    }

    public void addPassword(String packID, String password){
        this.password.put(packID,password);
        notifyObserver();
    }

    public void removePassword(String packID){
        password.remove(packID);
        notifyObserver();
    }

    public void addPackageToDeliveryMan(String delID, String packID) {
        DeliveryMan del;
        Package unasspack;
        if((del=getDeliveryMan(delID))!=null && (unasspack=getUnassignedPackage(packID))!=null){
            del.addPackage(unasspack);
            notifyObserver();
        } else {
            new ErrorGUIMain("Invalid ID!", false);
        }
    }

    public Package getPackage(String id){
        for(Package pack : packages){
            if(pack.getId().equals(id)){
                return pack;
            }
        }
        return null;
    }

    public DeliveryMan getDeliveryMan(String id){
        for(DeliveryMan del : deliveryMen){
            if(del.getId().equals(id)){
                return del;
            }
        }
        return null;
    }

    public DeliveryMan getDeliveryManFromPackID(String packID){
        for(DeliveryMan del: deliveryMen){
            if(del.hasPackage(packID)){
                return del;
            }
        }
        return null;
    }

    public ArrayList<DeliveryMan> getDeliveryMenList(){
        return deliveryMen;
    }

    public ArrayList<Package> getPackagesList(){
        return packages;
    }

    public HashMap<String, String> getPasswordList() {
        return password;
    }

    public ArrayList<Package> getUnassignedPackagesList(){
        ArrayList<Package> unassignedPackages = new ArrayList<>();
        for(Package pack : packages){
            if(getDeliveryManFromPackID(pack.getId())==null && password.get(pack.getId())==null){
                unassignedPackages.add(pack);
            }
        }
        return unassignedPackages;
    }

    public ArrayList<Package> getInTransitPackagesList(){
        ArrayList<Package> assignedPackages = new ArrayList<>();
        for(Package pack:packages){
            if(getDeliveryManFromPackID(pack.getId())!=null) {
                assignedPackages.add(pack);
            }
        }
        return assignedPackages;
    }

    public Package getUnassignedPackage(String id){
        for(Package pack : getUnassignedPackagesList()){
            if(pack.getId().equals(id)){
                return pack;
            }
        }
        return null;
    }

    public void addDeliveryDate(String text) throws IOException {
        readWriteDeliveryDate.insertText(text);
    }

    public void removeDeliveryDate(String packID) throws IOException {
        readWriteDeliveryDate.removeText(packID);
    }

    public String generateDeliveryManCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password = new String();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(35);
            char c = letters.charAt(n);
            password = password + c;
        }
        return password;
    }

    public void addObserver(Observer ob){
        observers.add(ob);
    }

    public void notifyObserver(){
        for(Observer ob : observers){
            ob.update();
        }
    }

    public void createServer() throws IOException {
        ManagerServer server = new ManagerServer(this);
        server.start();
    }

    public void createGUI(){
        ManagerFrame mf = new ManagerFrame(this);
    }

    @Override
    public void update(){
        packages.clear();
        updatePackages();
    }

}
