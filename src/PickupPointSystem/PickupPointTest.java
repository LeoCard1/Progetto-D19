package PickupPointSystem;

import PickupPointSystem.DatabaseSystem.Mappers.MainServerConnector;
import ServerAndDatabase.MainServer;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * This test class is used to check whether
 * it is possible to connect to the server.
 * A subsequent test adds a package and
 * then removes it in order to check if
 * everything works correctly
 * @author Gruppo D19
 * @version 1.0.0
 */

public class PickupPointTest {
    private static PickupPoint pickupPoint;

    /**
     * Before carrying out the tests, the
     * main server must be launched
     */

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





    /**
     * This method adds a package, then
     * checks that inputting a wrong code
     * doesn't stop or impede the server.
     * Finally, it removes the package.
     * The last lines make sure the server
     * won't be closed before carrying out
     * all the necessary operations
     */

    @org.junit.Test
    public void addAndRemovePackage() {
        try {
            MainServerConnector serverConnector = new MainServerConnector();
            serverConnector.addTestPackages();
            serverConnector.close();

            LoginDelMan delMan = new LoginDelMan(pickupPoint);
            delMan.login("test", "test");
            delMan.addDeliverymanPackages();

            assertFalse(pickupPoint.emptyBox("12345"));

            serverConnector = new MainServerConnector();
            String password = serverConnector.getTestPackageCode().replaceAll("\n", "");
            serverConnector.close();

            assertTrue(pickupPoint.emptyBox(password));
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }

    /**
     * This class makes sure the server
     * won't be closed before carrying out
     * all the necessary operations
     */

    @org.junit.After
    public void afterAddAndRemovePackage() {
        try {
            LoginDelMan delMan = new LoginDelMan(pickupPoint);
            delMan.login("test", "test");
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }

    /**
     * This method checks that inputting
     * a wrong deliveryman ID will return
     * 'null'
     */

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