package Management;

import DeliveryManSystem.DeliveryMan;
import Management.FileManager.ReadWritePackagesList;
import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import LockerSystem.Package;
import Management.FileManager.ReadWriteDeliveryDate;
import ObserverPattern.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Manager implements Observer {

    /*
     *  -deliveryManCode: password che deve inserire il DeliveryMan per accedere al PickupPoint.
     *  -deliveryMen: lista di DeliveryMan.
     *  -packages: lista di tutti i Package.
     *  -password: HashMap di packID associati a password per sbloccare la box in cui sono stati
     *  inseriti.
     *  -readWritePackagesList: reader e writer per il file PackageList.
     *  -readWriteDeliveryDate: reader e writer per il file DeliveryDate.
     *  -server: server del Manager per ricevere richiete da DeliveryManClient e PickupPointClient.
     */

    private String deliveryManCode;
    private ArrayList<DeliveryMan> deliveryMen = new ArrayList<>();
    private ArrayList<Package> packages = new ArrayList<>();
    private HashMap<String,String> password = new HashMap<>();
    private ReadWritePackagesList readWritePackagesList = new ReadWritePackagesList();
    private ReadWriteDeliveryDate readWriteDeliveryDate = new ReadWriteDeliveryDate();
    private ManagerServer server = new ManagerServer(this);


    public Manager() throws IOException {
        readWritePackagesList.addObserver(this);
        addPackages();
    }

    /*  -addPackages: consente di aggiungere pacchi alla lista prendendo le
     *  informazioni dal file di testo ReadWritePackagesList, se il pacco è gia
     *  presente non viene aggiunto.
     *  -removePackage: rimuove dal file PackagesList il pacco passato come argomento,
     *  quindi ReadWritePackagesList notifichera il manager del cambiamento che quindi
     *  effettuerà un update aggiornando l'arraylist packages.
     *  -addPassword: aggiunge all'HashMap ID del pacco e relativa password per sbloccare
     *  la box.
     *  -removePassword: rimuove dall'HashMap ID del pacco e relativa password dato l'ID
     *  in input.
     *  -addPackageToDeliveryMan: permette di aggiungere pacchi ad un determinato DeliveryMan
     *  dati ID del DeliveryMan e ID del pacco.
     *  -addDeliveryDate: permette di aggiungere date di consegna al file DeliveryDate.
     *  -removeDeliveryDate: permette di rimuovere date di consegna al file DeliveryDate
     *  dato l'ID del pacco.
     *  -update: aggiorna la lista di Packages dopo un cambiamento nel file PackagesList.
     */

    public void createDeliveryMan(String id) {
        DeliveryMan del = new DeliveryMan(id);
        deliveryMen.add(del);
    }

    public void createPackage(String id, double height, double length, double width) {
        Package pack = new Package(id, height, length, width);
        packages.add(pack);
    }

    public void addPackages() {
        try {
            String text = readWritePackagesList.getText();
            StringTokenizer st1 = new StringTokenizer(text, "\n");
            while(st1.hasMoreTokens()) {
                String riga = st1.nextToken();
                StringTokenizer st2 = new StringTokenizer(riga, "   ");
                String id = st2.nextToken();
                if(getPackage(id)==null) {
                    createPackage(id, Double.parseDouble(st2.nextToken()), Double.parseDouble(st2.nextToken()), Double.parseDouble(st2.nextToken()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            ErrorGUIMain guiError = new ErrorGUIMain(e.getMessage(), true);
        }
    }

    public void removePackage(Package pack) throws IOException {
        readWritePackagesList.removeText(pack.getId());
    }

    public void addPassword(String packID, String password){
        this.password.put(packID,password);
    }

    public void removePassword(String packID){
        password.remove(packID);
    }

    public DeliveryMan getDeliveryMan(String id){
        for(DeliveryMan del : deliveryMen){
            if(del.getId().equals(id)){
                return del;
            }
        }
        return null;
    }

    public Package getPackage(String id){
        for(Package pack : packages){
            if(pack.getId().equals(id)){
                return pack;
            }
        }
        return null;
    }

    public void addPackageToDeliveryMan(String delID, String packID) {
        try{
            getDeliveryMan(delID).addPackage(getPackage(packID));
        }  catch(Exception e){
            System.err.println("Invalid ID");
        }
    }

    public void setDeliveryManCode(String code){
        deliveryManCode = code;
    }

    public void addDeliveryDate(String text) throws IOException {
        readWriteDeliveryDate.insertText(text);
    }

    public void removeDeliveryDate(String packID) throws IOException {
        readWriteDeliveryDate.removeText(packID);
    }

    @Override
    public void update(){
        packages.clear();
        addPackages();
    }

}
