package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.PersistenceFacade;
import PickupPointSystem.DatabaseSystem.Tables.Delivery;
import PickupPointSystem.GraphicalInterface.GraIntMain;
import PickupPointSystem.LockerSystem.BoxType.*;
import PickupPointSystem.DatabaseSystem.Tables.Package;

import PickupPointSystem.ObserverPattern.Observer;
import PickupPointSystem.Server.NotificationSystem.DeliveryMail;
import PickupPointSystem.Server.NotificationSystem.EMailSender;
import PickupPointSystem.Server.PickupPointServer;

import java.io.IOException;
import java.util.*;

/**
 * @author Andrea Stella
 * @version 1.0
 */


public class PickupPoint {

    private String id;
    private String ip;
    private String address;
    private String location;
    private ArrayList<Box> boxList = new ArrayList<>();
    private HashMap<String, Box> unavailablesBoxes = new HashMap<>();
    private ArrayList<Observer> obsList = new ArrayList<>();
    private PersistenceFacade facade = new PersistenceFacade();
    private int smallBoxes;
    private int mediumBoxes;
    private int largeBoxes;

    /**
     * The constructor.Adds the boxes to the list, updates the boxes from the database,
     * creates the server and the graphical interface.
     * @param id
     */

    public PickupPoint(String id) {
        try {
            PickupPointSystem.DatabaseSystem.Tables.PickupPoint piPo = facade.getPickupPoint(id);
            if (piPo == null) throw new Exception("Error.");

            this.id = piPo.getId();
            this.location = piPo.getLocation();
            this.ip = piPo.getIp();
            this.smallBoxes = Integer.parseInt(piPo.getSmall_boxes());
            this.mediumBoxes = Integer.parseInt(piPo.getMedium_boxes());
            this.largeBoxes = Integer.parseInt(piPo.getLarge_boxes());
            this.address = piPo.getAddress();


            for(int i = 0; i < smallBoxes; i++){
                boxList.add(new SmallBox());
            }
            for(int i = 0; i < mediumBoxes; i++){
                boxList.add(new MediumBox());
            }
            for(int i = 0; i < largeBoxes; i++){
                boxList.add(new LargeBox());
            }

            updateBox();
            createGUI();
            createServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method adds the package to a specific box, adds the box to the available boxes
     * by setting the password, updates the database delivery and sends the delivery mail.
     * @param pack
     * @return boxNumber
     */

    public int addPackage(Package pack) {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize()) > -1){
                box.addPackage(pack);
                Date dateOfDelivery = new Date();
                box.setDate(dateOfDelivery);
                String password = box.generateBoxPassword();
                unavailablesBoxes.put(password, box);

                Delivery delivery = facade.getDeliveryFromPackID(id, pack.getId());
                delivery.setDateOfDelivery(dateOfDelivery);
                delivery.setBoxNumber(box.getBoxNumber());
                delivery.setBoxPassword(password);
                facade.updateDelivery(delivery);

                DateHandler dateHandler = new DateHandler();
                String email = facade.getPackage(pack.getId()).getCustomerEmail();
                DeliveryMail notify = new DeliveryMail(email, id, pack.getId(), password, dateHandler.addDays(dateOfDelivery,3), location, address);
                notify.start();
                notifyObservers();
                return box.getBoxNumber();
            }
        }
        return 0;
    }

    /**
     * This method empties the box associated with the entered password and remove it from
     * the unavailablesBoxes, remove the package and delivery from the database.
     * @param cod
     */

    public boolean emptyBox(String cod){
        Box box = null;
        Package pack = null;
        box = unavailablesBoxes.get(cod);
        if(box == null){
            return false;
        }
        pack = box.getPack();
        box.removePackage();
        unavailablesBoxes.remove(cod);
        facade.removeDelivery(pack.getId());
        facade.removePack(pack.getId());
        notifyObservers();
        return true;
    }

    /**
     * This method updates the status of the boxes from the database.
     */

    public void updateBox(){
        for(Box box : boxList){
            Delivery delivery = facade.getDeliveryFromBoxNumber(id, box.getBoxNumber());
            if(delivery!=null){
                box.addPackage(facade.getPackage(delivery.getPackID()));
                box.setDate(delivery.getDateOfDelivery());
                unavailablesBoxes.put(delivery.getBoxPassword(),box);
            }
        }
        facade.clearDeliveryCache();
        notifyObservers();
    }

    public int getSmallBoxes() {
        return smallBoxes;
    }

    public int getMediumBoxes() {
        return mediumBoxes;
    }

    public int getLargeBoxes() {
        return largeBoxes;
    }

    public Box getBoxFromIndex(int index) {
        return boxList.get(index);
    }

    public String getId(){
        return id;
    }

    private void createServer() {
        PickupPointServer server = new PickupPointServer(this);
        server.start();
    }

    private void createGUI() {
        new GraIntMain(this);
    }

    public void addObserver(Observer obs) {
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
