package DeliveryManSystem;


import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws Exception {


        DeliveryMan deliveryMan = new DeliveryMan("ANDREA", "ECAKE91749");
        deliveryMan.sendCredentials("PAV01");
        Gui delGui = new Gui();


    }
}
