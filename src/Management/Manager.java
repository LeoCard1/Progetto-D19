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

    /*
     *  -deliveryManCode: password che deve inserire il DeliveryMan per accedere al PickupPoint.
     *  -deliveryMen: lista di DeliveryMan.
     *  -packages: lista di tutti i Package.
     *  -unassignedPackages: lista dei pacchi non ancora affidati ad un corriere.
     *  -password: HashMap di packID associati a password per sbloccare la box in cui sono stati
     *  inseriti.
     *  -readWritePackagesList: reader e writer per il file PackageList.
     *  -readWriteDeliveryDate: reader e writer per il file DeliveryDate.
     *  -server: server del Manager per ricevere richiete da DeliveryManClient e PickupPointClient.
     */

    private ArrayList<DeliveryMan> deliveryMen = new ArrayList<>();
    private ArrayList<Package> packages = new ArrayList<>();
    private ArrayList<Package> unassignedPackages = new ArrayList<>();
    private HashMap<String,String> password = new HashMap<>();
    private ReadWritePackagesList readWritePackagesList = new ReadWritePackagesList();
    private ReadWriteDeliveryDate readWriteDeliveryDate = new ReadWriteDeliveryDate();
    private ManagerServer server = new ManagerServer(this);
    private ArrayList<Observer> observers = new ArrayList<>();


    public Manager() throws IOException {
        createGUI();
        readWritePackagesList.addObserver(this);
        updatePackages();
        notifyObserver();
    }

    /*
     *  -updatePackages: consente di aggiungere pacchi alla lista prendendo le
     *  informazioni dal file di testo ReadWritePackagesList, se il pacco è gia
     *  presente non viene aggiunto.
     *  -updateUnassignedPackages: aggiorna i pacchi che non sono stati assegnati ad
     *  un corriere controllando se il pacco è assegnato ad un deliveryman oppure
     *  è presente nell'HashMap password in cui sono presenti i pacchi consegnati,
     *  viene aggiornata la lista dopo un updatePackages o dopo un addPackToDeliveryMan.
     *  -removePackage: rimuove dal file PackagesList il pacco passato come argomento,
     *  quindi ReadWritePackagesList notifichera il manager del cambiamento che quindi
     *  effettuerà un update aggiornando l'arraylist packages.
     *  -addPassword: aggiunge all'HashMap ID del pacco e relativa password per sbloccare
     *  la box.
     *  -removePassword: rimuove dall'HashMap ID del pacco e relativa password dato l'ID
     *  in input.
     *  -addPackageToDeliveryMan: permette di aggiungere pacchi ad un determinato DeliveryMan
     *  dati ID del DeliveryMan e ID del pacco, è consentito solo se il pacco non è stato
     *  assegnato a nessun altro deliveryman, una volta aggiunto il pacco viene rimosso
     *  dagli unassignedPackage.
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
                ErrorGUIMain guiError = new ErrorGUIMain("Existing ID", true);
                return false;
            }
        } else {
            ErrorGUIMain guiError = new ErrorGUIMain("ID too short!", true);
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
            ErrorGUIMain guiError = new ErrorGUIMain(e.getMessage(), true);
        }
        updateUnassignedPackages();
        notifyObserver();
    }

    public void updateUnassignedPackages(){
        unassignedPackages.clear();
        if(deliveryMen.size()==0){
            for(Package pack : packages){
                unassignedPackages.add(pack);
            }
            notifyObserver();
            return;
        }
        for(Package pack : packages){
            if(getDeliveryManFromPackID(pack.getId())==null && password.get(pack.getId())==null){
                unassignedPackages.add(pack);
            }
        }
        notifyObserver();
    }

    public void removePackage(Package pack) throws IOException {
        readWritePackagesList.removeText(pack.getId());
        notifyObserver();
    }

    public void addPassword(String packID, String password){
        this.password.put(packID,password);
        notifyObserver();
    }

    public void removePassword(String packID){
        password.remove(packID);
        notifyObserver();
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
        return unassignedPackages;
    }

    public ArrayList<Package> getInTransitPackagesList(){
        ArrayList<Package> assignedPackages = new ArrayList<>();
        for(Package pack:packages){
            if(getUnassignedPackage(pack.getId())==null && password.get(pack.getId())==null) {
                assignedPackages.add(pack);
            }
        }
        return assignedPackages;
    }

    public Package getPackage(String id){
        for(Package pack : packages){
            if(pack.getId().equals(id)){
                return pack;
            }
        }
        return null;
    }

    public Package getUnassignedPackage(String id){
        for(Package pack : unassignedPackages){
            if(pack.getId().equals(id)){
                return pack;
            }
        }
        return null;
    }

    public void addPackageToDeliveryMan(String delID, String packID) {
        DeliveryMan del;
        Package unasspack;
       if((del=getDeliveryMan(delID))!=null && (unasspack=getUnassignedPackage(packID))!=null){
           del.addPackage(unasspack);
           updateUnassignedPackages();
       } else {
           ErrorGUIMain guiError = new ErrorGUIMain("Invalid ID!", true);
       }
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

    public void createGUI(){
        ManagerFrame mf = new ManagerFrame(this);
    }

    @Override
    public void update(){
        packages.clear();
        updatePackages();
    }

}
