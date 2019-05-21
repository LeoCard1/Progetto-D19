package DeliveryManSystem;


import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        DeliveryManClient andrea = new DeliveryManClient();
        andrea.logIn("Andrea","EO85MK4K15");
        andrea.updateList();
        andrea.sendList();


    }
}
