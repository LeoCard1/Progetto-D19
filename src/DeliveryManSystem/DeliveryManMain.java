package DeliveryManSystem;


import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        Gui gui = new Gui();
        DeliveryManClient andrea = new DeliveryManClient();
        andrea.logIn("Andrea","G63RGILO07");
        andrea.updateList();
        andrea.sendList();


    }
}
