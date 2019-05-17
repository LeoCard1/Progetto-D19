package DeliveryManSystem;

import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;


import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        DeliveryMan andrea = new DeliveryMan("Andrea");
        andrea.updatePackageList();
        andrea.sendPackageList();
        Gui gui = new Gui();
        gui.ClientGUI();
    }
}
