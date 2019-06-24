package DeliveryManSystem;


import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws Exception {
       /*
        andrea.logIn("Andrea, ECAKE91749");
        andrea.sendList();*/

       // DeliveryManClient andrea = new DeliveryManClient();
        DeliveryMan deliveryMan = new DeliveryMan("ANDREA", "ECAKE91749");
        deliveryMan.sendCredentials("PAV01");

      //  Gui delGui = new Gui();
    }
}
