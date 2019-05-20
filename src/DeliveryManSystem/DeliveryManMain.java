package DeliveryManSystem;


import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {

        DeliveryManClient andrea = new DeliveryManClient();
        Gui gui = new Gui(andrea);
        //andrea.logIn("Andrea","03H4Y7IKWT");
        //andrea.updateList();
       // andrea.sendList();


    }
}
