package DeliveryManSystem;


import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        DeliveryManClient andrea = new DeliveryManClient();
        andrea.logIn("Andrea","H6RBDUGWGC");
        andrea.updateList();
        andrea.sendList();


    }
}
