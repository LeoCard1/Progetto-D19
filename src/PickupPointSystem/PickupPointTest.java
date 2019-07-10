/*
package PickupPointSystem;


import PickupPointSystem.DatabaseSystem.Tables.PackageTable;
import PickupPointSystem.GraphicalInterface.GraIntMain;
import PickupPointSystem.LockerSystem.BoxType.Box;
import ServerAndDatabase.MainServer;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PickupPointTest {

    private PickupPoint pickupPoint;

    @org.junit.Before
    public void setUp() throws Exception {
       // MainServer server = new MainServer();
        //server.start();
       //new GraIntMain("PAV01");
        ArrayList<Box> boxList = new ArrayList<>();
        pickupPoint=  new PickupPoint("PAV01");

        assertNotNull("Pickup creato",pickupPoint);
        assertNotNull("boxList creata",boxList);



    }

    @org.junit.After
    public void tearDown() throws Exception {
    }


    @org.junit.Test
    public void addPackage() throws IOException{
        pickupPoint.updateBox();
        PackageTable packageTable = new PackageTable("packTabId",3.5,4.5,5.5,"smartlocker.d19@gmail.com");
        pickupPoint.addPackage(packageTable);
        assertTrue(pickupPoint.getUnavailablesBoxes().containsKey("packTabId"));

        pickupPoint.emptyBox("packTabId");
        assertFalse(pickupPoint.getUnavailablesBoxes().containsKey("packTabId"));
    }

    @org.junit.Test
    public void emptyBox() {
    }



    @org.junit.Test
    public void updateBox() {
    }

    @org.junit.Test
    public void getSmallBoxes() {
    }

    @org.junit.Test
    public void getMediumBoxes() {
    }

    @org.junit.Test
    public void getLargeBoxes() {
    }

    @org.junit.Test
    public void getBoxFromIndex() {
    }

    @org.junit.Test
    public void getId() {
    }

}
*/