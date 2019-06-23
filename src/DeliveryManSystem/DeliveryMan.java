package DeliveryManSystem;


import DeliveryManSystem.Client.DeliveryManClient;
import DeliveryManSystem.DatabaseSystem.PersistenceFacade;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryManTable;
import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;


import java.util.ArrayList;

/**
 * @author Andrea Stella
 * @version 2.0
 */

public class DeliveryMan {

    private PersistenceFacade facade = new PersistenceFacade();
    private DeliveryManClient client = new DeliveryManClient();
    private ArrayList<DeliveryTable> deliveries = new ArrayList<>();
    private String id;
    private String password;

    public DeliveryMan(String id, String password) throws Exception {
        DeliveryManTable delManTable = facade.getDeliveryMan(id);
        if(delManTable == null || !delManTable.getPassword().equals(password)) throw new Exception("Error.");

        this.password = password;
        this.id = id;

        updateDeliveries();
    }

    public DeliveryManClient getClient(){
        return client;
    }

    public void sendCredentials(){
        client.connectToPickupPoint(id, password);
    }

    public void updateDeliveries(){
        deliveries.clear();
        for(DeliveryTable delivery : facade.getDeliveries(id)){
            if(!delivery.wasMade()){
                deliveries.add(delivery);
            }
        }

    }












}
