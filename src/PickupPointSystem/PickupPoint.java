package PickupPointSystem;

import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import PickupPointSystem.GraphicalInterface.GraIntMain;
import LockerSystem.BoxType.*;
import LockerSystem.Package;

import LockerSystem.Size;
import ObserverPattern.Observer;

import java.io.IOException;
import java.util.*;


public class PickupPoint {

    /**
     *  -id: codice identificativo PickupPoint.
     *  -boxList: lista contenente tutte le Box.
     *  -availableBox: HashMap contenente le Box piene associate alla password per aprirle.
     *  -obsList: lista di observer.
     *  -piPoClient: client del PickupPoint, comunica col ManagerServer, viene utilizzato per
     *  notificare al Manager consegne e ritiri di pacchi.
     */

    private String id;
    private ArrayList<Box> boxList = new ArrayList<>();
    private HashMap<String, Box> availableBox = new HashMap<>();
    private ArrayList<Observer> obsList = new ArrayList<>();
    private PickupPointClient piPoClient = new PickupPointClient();

    public PickupPoint(String id, int numSmallBox, int numMediumBox, int numLargeBox) throws IOException {
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
        createServer();
        createGUI();
    }

    /**
     *  -addPackage: consente di aggiungere pacchi alla lista, inoltre viene generata una password
     *  per sbloccare la box, quindi viene associata alla box aggiungendo password e box all'HashMap
     *  availableBox, viene inoltre notificato il ManagerServer dell'aggiunta del pacco. Restituisce
     *  il codice della box in cui è stato inserito il pacco.
     *  -emptyBox: viene svuotata la box associata al codice passato come argomento, viene inoltre
     *  notificato il ManagerServer del ritiro del pacco.
     *  -generateBoxPassword: genera password per sbloccare box in base al toString della box.
     */

    public ArrayList<Box> getBoxList() {
        return boxList;
    }

    public int addPackage(Package pack) throws IOException {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize()) > -1){
                box.addPackage(pack);
                String password = generateBoxPassword(box.toString());
                availableBox.put(password, box);
                piPoClient.notifyOfPackageAdded(box.toString(),password);
                notifyObservers();
                return box.getCode();
            }
        }
        return 0;
    }

    public void emptyBox(String cod){
        Box box = null;
        Package pack = null;
        try {
            box = availableBox.get(cod);
            pack = box.getPack();
            box.removePackage();
            availableBox.remove(cod);
            piPoClient.notifyOfPackagePickedUp(pack.getId());
            notifyObservers();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            new ErrorGUIMain("the code is invalid", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Box getBoxFromIndex(int index) {
        return boxList.get(index);
    }

    public Size getBoxSizeGivenIndex(int index) {
        return getBoxFromIndex(index).getSize();
    }


    public String generateBoxPassword(String boxToString) {
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

    private void createServer() throws IOException {
        PickupPointServer server = new PickupPointServer(this);
        server.start();
    }

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

    private void notifyObservers(){
        for (Observer obsOfList : obsList) {
            obsOfList.update();
        }
    }

}
