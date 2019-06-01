package DeliveryManSystem;


import java.io.IOException;
import DeliveryManSystem.Client.DeliveryManClient;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        DeliveryManClient andrea = new DeliveryManClient();
        andrea.connectToPickupPoint();

    }
}
