package Management;

import LockerSystem.BoxType.*;
import LockerSystem.DeliveryMan;
import LockerSystem.Package;

import java.io.IOException;
import java.util.*;


public class PickupPoint {
    private String id;
    private ArrayList<Box> boxList;
    private int numSmallBox;
    private int numMediumBox;
    private int numLargeBox;
    private String deliveryManID;
    private HashMap<String, Box> availableBox;

    /* boxList è la lista che comprende tutte le box, sia quelle piene che quelle vuote:
     * Viene usato per inserire i pacchi al momento della consegna.
     *
     * availableBox serve per controllare le box che vengono aperte dai clienti:
     * associando alle box il rispettivo codice (generatePassword) unico per ogni cliente, basterà usare quello
     * per svuotarle.
     * -RG */

    public PickupPoint(String id, int numSmallBox, int numMediumBox, int numLargeBox){
        this.id = id;
        boxList = new ArrayList<>();
        this.numSmallBox = numSmallBox;
        this.numMediumBox = numMediumBox;
        this.numLargeBox = numLargeBox;
        deliveryManID = null;
        availableBox = new HashMap<>();
        for(int i = 0; i < numSmallBox; i++){
            boxList.add(new SmallBox());
        }
        for(int i = 0; i < numMediumBox; i++){
            boxList.add(new MediumBox());
        }
        for(int i = 0; i < numLargeBox; i++){
            boxList.add(new LargeBox());
        }
    }

    public ArrayList<Box> getBoxList() {
        return boxList;
    }
    public int getNumSmallBox() {
        return numSmallBox;
    }
    public int getNumMediumBox() {
        return numMediumBox;
    }
    public int getNumLargeBox() {
        return numLargeBox;
    }


    public int addPackage(Package pack) throws IOException {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize()) > -1){
                box.addPackage(pack);
                DeliveryDateWriter ddw = new DeliveryDateWriter() ;
                ddw.insertText(box.toString());
                availableBox.put(generatePassword(box.toString()), box);
    // la password generata è il codice "unico" che viene inviato per messaggio al cliente, lo conosce solo lui -RG
                return box.getCode();
            }
        }
        return 0;
    }


    // Ci serve davvero un modo per svuotare le box conoscendo il pacchetto contenuto?
    // Secondo me basta pickupPackage che si basa sul codice (ossia il sistema che utilizzano i clienti) -RG
    public boolean removePackage(Package pack) throws IOException {
        for(Box box : boxList){
            if(box.getPack() == pack){
                DeliveryDateWriter ddw = new DeliveryDateWriter() ;
                ddw.removeText(box.getCode());
                box.removePackage();
                return true;
            }
        }
        return false;
    }


    // A seconda del codice immesso, svuota la rispettiva box e la elimina dall'Hash Map "availableBox" -RG
    private void emptyBox(String cod) throws IOException {
        Box b = null;
        try {
            b = availableBox.get(cod);
            DeliveryDateWriter ddw = new DeliveryDateWriter() ;
            ddw.removeText(b.getCode());
            b.removePackage();
            availableBox.remove(cod);
        }
        catch (NullPointerException e) {System.out.println("ERROR: The code is not valid.");}
    }

    public void pickupPackage(String cod) {
        try {emptyBox(cod);}
        catch (IOException e) {System.out.println("Errore.");}
        }


    public void sortPackages (DeliveryMan del, String cod) {
        // qui il cod da utilizzare è l'ID del fattorino -RG
        if (!Objects.equals(cod, deliveryManID)) {
            System.out.println("Errore: the ID is incorrect.");
            return;
        }
        ArrayList<Package> delPacks = del.getListaPacchi();
        for (Package p : delPacks) {
            try {addPackage(p);}
            catch (IOException e) {System.out.println("Errore.");}
        }
    }


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
        password = password + division[0] + division[2] + division[1].charAt(0);
        return password.replaceAll("\\s+","");
    }

    // La password per il fattorino è formata da 10 caratteri alfanumerici maiuscoli (ES: A30FLEB5W3) -RG
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


}
