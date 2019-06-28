package DeliveryManSystem;


import DeliveryManSystem.Client.DeliveryManClient;
import DeliveryManSystem.DatabaseSystem.PersistenceFacade;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryManTable;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;


import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Andrea Stella
 * @version 2.0
 */

public class DeliveryMan {

    private PersistenceFacade facade = new PersistenceFacade();
    private DeliveryManClient client = new DeliveryManClient();
    private ArrayList<DeliveryTable> deliveries = new ArrayList<>();
    private ArrayList<DeliveryTable> deliveriesExpired = new ArrayList<>();
    private String id;
    private String password;

    public DeliveryMan(String id, String password) throws Exception {
        DeliveryManTable delManTable = facade.getDeliveryMan(id);
        if(delManTable == null || !delManTable.getPassword().equals(password)) throw new Exception("Error.");

        this.password = password;
        this.id = id;

        updateDeliveries();
        updateDeliveriesExpired();
    }

    public void sendCredentials(String piPoID) throws IOException {
        PickupPointTable piPoTable = facade.getPickupPoint(piPoID);

        client.connectToPickupPoint(piPoTable.getIp(), id, password);
    }

    public void updateDeliveries() throws IOException {
        deliveries.clear();
        for(DeliveryTable delivery : facade.getDeliveries(id)){
            if(!delivery.wasMade()){
                deliveries.add(delivery);
            }
        }
    }

    public void updateDeliveriesExpired() throws IOException {
        deliveriesExpired.clear();
        for(DeliveryTable delivery : facade.getDeliveries(id)){
            if(delivery.wasMade() && delivery.hasPackDeliveredForThreeDays()){
                deliveriesExpired.add(delivery);
            }
        }
    }





}
