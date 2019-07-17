package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.Mappers.MainServerConnector;
import ServerAndDatabase.ServerMain;

import java.io.IOException;

import static org.junit.Assert.*;

public class PickupPointTest {
    private static ServerMain serverMain;
    private static PickupPoint pickupPoint;

    @org.junit.BeforeClass
    public static void startMainServer() {
        try {
            serverMain = new ServerMain();
            pickupPoint = new PickupPoint("TEST");
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }

    @org.junit.Test
    public void addPackage() {
        try {
            MainServerConnector serverConnector = new MainServerConnector();
            serverConnector.addTestPackages();
            serverConnector.close();

            LoginDelMan delMan = new LoginDelMan(pickupPoint);
            delMan.login("test", "test");
            delMan.addDeliverymanPackages();
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }

    @org.junit.Test
    public void emptyBox() {
        try {
            MainServerConnector serverConnector = new MainServerConnector();
            String password = serverConnector.getTestPassword().replaceAll("\n", "");
            serverConnector.close();

            assertTrue(pickupPoint.emptyBox(password));
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }
}