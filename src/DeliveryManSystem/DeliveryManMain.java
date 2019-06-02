package DeliveryManSystem;


import DeliveryManSystem.Client.DatabaseOperations;
import DeliveryManSystem.Client.DeliveryManClient;

import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        /*DeliveryManClient andrea = new DeliveryManClient();
        andrea.logIn("Andrea","ECAKE91749");
        andrea.sendList();*/

        /* Prova per connettersi al database */
        DeliveryManClient andrea = new DeliveryManClient();
        /*DatabaseOperations dop = new DatabaseOperations();
        dop.synchronizePackages();*/
    }
}
