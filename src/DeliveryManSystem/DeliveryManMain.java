package DeliveryManSystem;


import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        DeliveryManClient andrea = new DeliveryManClient();
        andrea.logIn("Andrea","WKD5C04JH5");
        andrea.sendList();

    }
}
