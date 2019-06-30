package DeliveryManSystem;


import DeliveryManSystem.Client.DeliveryManClient;
import DeliveryManSystem.DatabaseSystem.PersistenceFacade;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryManTable;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;
import DeliveryManSystem.Exceptions.UsernameOrPasswordException;
import PickupPointSystem.ObserverPattern.Observer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This class represents the delivery man, there are methods to send
 * the credentials to the pickup point and methods that return the
 * deliveries to do and those expired.
 * @author Andrea Stella
 * @version 2.0
 */

public class DeliveryMan {

    private PersistenceFacade facade = new PersistenceFacade();
    private DeliveryManClient client = new DeliveryManClient();
    private String id;
    private String password;

    /**
     * The constructor. Authenticates the delivery man with the id and
     * password passed as an argument, in case of successful authentication
     * the instance is created, otherwise it is not created
     * @param id the delivery man id
     * @param password the delivery man password
     * @throws IOException if the server is not available
     */

    public DeliveryMan(String id, String password) throws IOException {
        DeliveryManTable delManTable = facade.getDeliveryMan(id);
        if(delManTable == null || !delManTable.getPassword().equals(password)) throw new UsernameOrPasswordException();

        this.password = password;
        this.id = id;
    }

    /**
     * Sends id and password to the pickup point specified by the pickup point
     * id passed as an argument
     * @param piPoID the pickup point id
     * @throws IOException if the server is not available
     */

    public void sendCredentials(String piPoID) throws IOException {
        PickupPointTable piPoTable = facade.getPickupPoint(piPoID);

        client.connectToPickupPoint(piPoTable.getIp(), id, password);
    }

    /**
     * This method returns an Arraylist of deliveries to be made
     * @return the created ArrayList of delivery
     * @throws IOException if the server is not available
     */

    public ArrayList<DeliveryTable> getDeliveries() throws IOException {
        ArrayList<DeliveryTable> deliveries = new ArrayList<>();
        for(DeliveryTable delivery : facade.getDeliveries(id)){
            if(!delivery.wasMade()){
                deliveries.add(delivery);
            }
        }
        return deliveries;
    }

    /**
     * This method created an Arraylist of deliveries carried out for at
     * least 3 days which are therefore associated with packages to be
     * collected
     * @return the created ArrayList of deliveries
     * @throws IOException if the server is not available
     */

    public ArrayList<DeliveryTable> getDeliveriesExpired() throws IOException {
        ArrayList<DeliveryTable> deliveriesExpired = new ArrayList<>();
        for(DeliveryTable delivery : facade.getDeliveries(id)){
            if(delivery.wasMade() && delivery.hasPackDeliveredForThreeDays()){
                deliveriesExpired.add(delivery);
            }
        }
        return deliveriesExpired;
    }

    /**
     * This method returns an Arraylist of pickup point id associated with deliveries
     * to be made and those that have expired
     * @return the created ArrayList of pickup point id
     * @throws IOException if the server is not available
     */

    public TreeSet<String> getPickupPointsID() throws IOException {
        TreeSet<String> pickupPointsID = new TreeSet<>();
        for(DeliveryTable delivery : facade.getDeliveries(id)){
            if(!delivery.wasMade() || (delivery.wasMade() && delivery.hasPackDeliveredForThreeDays())) {
                pickupPointsID.add(delivery.getPipoID());
            }
        }
        return pickupPointsID;
    }


}
