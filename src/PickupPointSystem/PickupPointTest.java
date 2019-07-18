package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.Mappers.MainServerConnector;
import ServerAndDatabase.MainServer;

import java.io.IOException;

import static org.junit.Assert.*;

public class PickupPointTest {
    private static PickupPoint pickupPoint;

    @org.junit.BeforeClass
    public static void startMainServer() {
        try {
            MainServer mainServer = new MainServer();
            mainServer.start();

            pickupPoint = new PickupPoint("TEST");
            pickupPoint.updateBox();
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }





    @org.junit.Test
    public void addPackageAndTestWrongCode() {
        try {
            MainServerConnector serverConnector = new MainServerConnector();
            serverConnector.addTestPackages();
            serverConnector.close();

            LoginDelMan delMan = new LoginDelMan(pickupPoint);
            delMan.login("test", "test");
            delMan.addDeliverymanPackages();

            assertFalse(pickupPoint.emptyBox("12345"));
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
            String password = serverConnector.getTestPackageCode().replaceAll("\n", "");
            serverConnector.close();

            assertTrue(pickupPoint.emptyBox(password));

            LoginDelMan delMan = new LoginDelMan(pickupPoint);
            delMan.login("test", "test");
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }

    @org.junit.Test
    public void wrongIDTest() {
        try {
            MainServerConnector serverConnector = new MainServerConnector();
            String password = serverConnector.deliveryManGet("testwrong").replaceAll("\n", "");
            serverConnector.close();

            assertTrue(password.equals("null"));
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }
}