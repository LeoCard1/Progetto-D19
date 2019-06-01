package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.PersistenceFacade;

import java.io.IOException;
import java.sql.SQLException;

public class PickupPointMain {
    public static void main(String[] args) throws IOException, SQLException {
        PickupPoint piPo = new PickupPoint("piPo",21,15,9);
        PersistenceFacade pf = new PersistenceFacade();
        System.out.println(pf.getDeliveryMan("ANDREA").getPassword());
    }
}
