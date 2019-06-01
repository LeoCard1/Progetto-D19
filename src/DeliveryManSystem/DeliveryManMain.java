package DeliveryManSystem;


import DeliveryManSystem.Client.DatabaseOperations;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        /*DeliveryManClient andrea = new DeliveryManClient();
        andrea.logIn("Andrea","WKD5C04JH5");
        andrea.sendList();*/

        /* Prova per connettersi al database */
        DeliveryMan sergio = new DeliveryMan("SERGIO", "EMCIL91742");
        DatabaseOperations dop = new DatabaseOperations(sergio);
        dop.synchronizePackages(sergio.getId(), sergio.getPassword());
    }
}
