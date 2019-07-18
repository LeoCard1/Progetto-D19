package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.PersistenceFacade;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryTable;
import PickupPointSystem.DatabaseSystem.Tables.PackageTable;
import PickupPointSystem.DatabaseSystem.Tables.PickupPointTable;
import PickupPointSystem.Exceptions.IncorrectIDException;
import PickupPointSystem.LockerSystem.BoxType.*;

import PickupPointSystem.Server.NotificationSystem.DeliveryMail;
import PickupPointSystem.Server.PickupPointServer;

import java.io.IOException;
import java.util.*;

/**
 * This class handles pick up and delivery in the Pickup Point
 * @author Andrea Stella
 * @version 1.0
 */

public class PickupPoint {

    private String id;
    private String ip;
    private String address;
    private String location;
    private ArrayList<Box> boxList = new ArrayList<>();
    private HashMap<String, Box> unavailableBoxes = new HashMap<>();
    private PersistenceFacade facade = new PersistenceFacade();
    private int smallBoxes;
    private int mediumBoxes;
    private int largeBoxes;

    /**
     * The constructor. Requests to the central server the information of the PickupPoint
     * associated to the id passed as an argument, through these it updates the attributes,
     * adds the boxes to the list and creates the server
     * @param id the Pickup Point id
     */

    public PickupPoint(String id) throws IOException {
        PickupPointTable piPo = facade.getPickupPoint(id);
        if (piPo == null) throw new IncorrectIDException();

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

        createServer();

    }

    /**
     * This method adds the package to the smaller box that can contain it, adds the box
     * to the available boxes by setting the password, updates the database delivery and
     * sends the delivery mail.
     * @param pack the package to be delivered
     */

    public void addPackage(PackageTable pack) throws IOException {
        Collections.sort(boxList);
        for(Box box : boxList){
            if(box.isAvailable() && box.getSize().compareTo(pack.getSize()) > -1){
                box.addPackage(pack);
                Date dateOfDelivery = new Date();
                String password = box.generateBoxPassword();
                unavailableBoxes.put(password, box);

                for(DeliveryTable delivery : facade.getDeliveries(id)){
                    if(delivery.hasPackage(pack.getId())){
                        delivery.setDateOfDelivery(dateOfDelivery);
                        delivery.setBoxNumber(box.getBoxNumber());
                        delivery.setBoxPassword(password);
                        facade.updateDelivery(delivery);
                    }
                }

                DateHandler dateHandler = new DateHandler();
                String email = facade.getPackage(pack.getId()).getCustomerEmail();
                DeliveryMail notify = new DeliveryMail(email, id, pack.getId(), password, dateHandler.addDays(dateOfDelivery,3), location, address);
                notify.start();
                return;
            }
        }
    }

    /**
     * This method empties the box associated with the entered password and remove it from
     * the unavailableBoxes, remove the package and delivery from the database.
     * @param cod the code to unlock the box
     */

    public boolean emptyBox(String cod) throws IOException {
        Box box = null;
        PackageTable pack = null;
        box = unavailableBoxes.get(cod);
        if(box == null){
            return false;
        }
        pack = box.getPack();
        unavailableBoxes.remove(cod);
        facade.removeDelivery(pack.getId());
        facade.removePack(pack.getId());
        box.removePackage();
        return true;
    }

    /**
     * This method updates the status of the boxes from the database.
     */

    public void updateBox() throws IOException {
        ArrayList<DeliveryTable> deliveries = facade.getDeliveries(id);
        for (DeliveryTable delivery : deliveries) {
            for (Box box : boxList) {
                if(delivery.hasBoxNumber(box.getBoxNumber())){
                    box.addPackage(facade.getPackage(delivery.getPackID()));
                    unavailableBoxes.put(delivery.getBoxPassword(),box);
                }
            }
        }
    }

    /**
     * @return the number of small boxes
     */

    public int getSmallBoxes() {
        return smallBoxes;
    }

    /**
     * @return the number of medium boxes
     */


    public int getMediumBoxes() {
        return mediumBoxes;
    }

    /**
     * @return the number of large boxes
     */


    public int getLargeBoxes() {
        return largeBoxes;
    }

    /**
     * This method returns the box of the ArrayList boxList from the index
     * passed as an argument
     * @param index the index relative to the box
     * @return the requested box
     */

    public Box getBoxFromIndex(int index) {
        return boxList.get(index);
    }

    /**
     * @return the PickupPoint id
     */

    public String getId(){
        return id;
    }

    /**
     * This method creates and starts the server
     */

    private void createServer() {
        PickupPointServer server = new PickupPointServer();
        server.start();
    }
}
