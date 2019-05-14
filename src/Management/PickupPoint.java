package Management;

import GraphicalInterface.GraIntMain;
import LockerSystem.BoxType.*;
import ClientSystem.DeliveryMan;
import LockerSystem.Package;

import LockerSystem.Size;
import ObserverPattern.Observer;

import java.io.IOException;
import java.util.*;


public class PickupPoint {
    private String id;
    private ArrayList<Box> boxList = new ArrayList<>();
    private String deliveryManID = null;
    private HashMap<String, Box> availableBox = new HashMap<>();
    private ArrayList<Observer> obsList = new ArrayList<>();

    /* boxList è la lista che comprende tutte le box, sia quelle piene che quelle vuote:
     * Viene usato per inserire i pacchi al momento della consegna.
     *
     * availableBox serve per controllare le box che vengono aperte dai clienti:
     * associando alle box il rispettivo codice (generatePassword) unico per ogni cliente, basterà usare quello
     * per svuotarle.
     * -RG */

    public PickupPoint(String id, int numSmallBox, int numMediumBox, int numLargeBox){
        this.id = id;
        for(int i = 0; i < numSmallBox; i++){
            boxList.add(new SmallBox());
        }
        for(int i = 0; i < numMediumBox; i++){
            boxList.add(new MediumBox());
        }
        for(int i = 0; i < numLargeBox; i++){
            boxList.add(new LargeBox());
        }
        createGUI();
    }

    public ArrayList<Box> getBoxList() {
        return boxList;
    }


    public void addPackage(Package pack) throws IOException {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize()) > -1){
                box.addPackage(pack);
                DeliveryDateWriter ddw = new DeliveryDateWriter() ;
                ddw.insertText(box.toString());
                String password = generatePassword(box.toString());
                availableBox.put(password, box);
                notifyObservers();
                System.out.println(password);
                return;
    // la password generata è il codice "unico" che viene inviato per messaggio al cliente, lo conosce solo lui -RG
            }
        }
    }

    // A seconda del codice immesso, svuota la rispettiva box e la elimina dall'Hash Map "availableBox" -RG
    public void emptyBox(String cod) throws IOException {
        Box b = null;
        try {
            b = availableBox.get(cod);
            DeliveryDateWriter ddw = new DeliveryDateWriter() ;
            ddw.removeText(b.getCode());
            b.removePackage();
            availableBox.remove(cod);
            notifyObservers();
        }
        catch (NullPointerException e) {System.out.println("ERROR: The code is not valid.");}
    }

    /*
    public void sortPackages (DeliveryMan del, String cod) {
        // qui il cod da utilizzare è l'ID del fattorino -RG
        if (!Objects.equals(cod, deliveryManID)) {
            System.out.println("Error: the ID is incorrect.");
            return;
        }

        ArrayList<Package> delPacks = del.getListaPacchi();

        for (Package p : delPacks) {
            try {
                addPackage(p);
            } catch (IOException e) {
                System.out.println("Errore.");
            }
        }

        notifyObservers();
    }
*/

    /* La password per i clienti è generata in questo modo:
     * primi 5 caratteri: lettere casuali dalla A alla Z
     * seguenti 1-2 caratteri: numero della box
     * seguenti 6 caratteri: numero della data (senza trattini)
     * ultimo carattere: primo carattere del nome del pacco (vedi sotto)
     * Esempio: ABLXU3514092019p
     * -RG */
    private String generatePassword(String boxToString) {
        /* Utilizzo l'array division:
         * [0] = numero della box
         * [1] = nome del pacco (l'id di quando si crea l'oggetto Package in GUITest)
         * [2] = la data senza trattini (GGMMAAAA)
         * -RG */
        String[] division = boxToString.split("\t");
        division[2] = division[2].replaceAll("\\D","");

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String password = new String();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int n = rand.nextInt(25);
            char c = letters.charAt(n);
            password = password + c;
        }
        password = password + division[0] + division[2];
        return password.replaceAll("\\s+","");
    }

    // La password per il fattorino è formata da 10 caratteri alfanumerici maiuscoli (ES: A30FLEB5W3) -RG
    /*
    public void generateDeliveryManID() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password = new String();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(35);
            char c = letters.charAt(n);
            password = password + c;
        }
        deliveryManID = password;
    }
    */

    public String getDeliveryManID() {
        return deliveryManID;
    }

    /*
    public DeliveryMan createDeliveryMan(String id) {
        DeliveryMan delman = new DeliveryMan(id);
        addObserver(delman);

        return delman;
    }
    */

    private void createGUI() {
        GraIntMain gui = new GraIntMain(this);
        addObserver(gui);
    }

    private void addObserver(Observer obs) {
        for (Observer obsOfList : obsList) {
            if (obs == obsOfList) return;
        }

        obsList.add(obs);
    }

    private void notifyObservers() {
        for (Observer obsOfList : obsList) {
            obsOfList.update();
        }
    }

    public Box getBoxFromIndex(int index) {
        return boxList.get(index);
    }

    public Size getBoxSizeGivenIndex(int index) {
        return getBoxFromIndex(index).getSize();
    }
}
