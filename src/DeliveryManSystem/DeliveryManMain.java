package DeliveryManSystem;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        DeliveryManClient andrea = new DeliveryManClient();

        andrea.logIn("Andrea","FW40AF8Y8X");
        andrea.sendList();

    }
}
