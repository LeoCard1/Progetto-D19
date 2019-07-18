package ServerAndDatabase;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * This test class is used to check that no
 * packages are assigned to the test pickup
 * point and that the test password has not
 * been altered
 * @author Gruppo D19
 * @version 1.0.0
 */

public class MainServerTest {
    private static Socket clientSocket = new Socket();
    private static BufferedReader in;
    private static PrintStream out;

    /**
     * Before testing, the main server needs
     * to be started. A client is necessary as
     * well in order to communicate with it
     */

    @org.junit.BeforeClass
    public static void beforeTest() {
        MainServer mainServer = new MainServer();
        mainServer.start();

        try {
            startClient();
        } catch (IOException e) {
            System.err.println("Error: can't connect to the server.");
            e.printStackTrace();

            fail();
        }
    }

    /**
     * After carrying out all the tests, this
     * method makes sure the connection will
     * be closed correctly
     */

    @org.junit.AfterClass
    public static void afterTest() {
        out.println("close");
    }





    /**
     * This method verifies that no packages
     * are assigned to the test pickup point
     */

    @org.junit.Test
    public void verifyNoTestPackages() {
        out.println("delivery getfrompipoid test");

        StringBuilder response = getResponse();
        assertEquals(response.toString().replaceAll("\n", ""), "");
    }

    /**
     * This method returns the test password
     */

    @org.junit.Test
    public void getDeliverymanPassword() {
        out.println("deliveryman get test");

        StringBuilder response = getResponse();
        assertEquals(response.toString().replaceAll("\n", ""), "test");
    }





    /**
     * This method creates a client in order to
     * communicate with the server
     * @throws IOException Client socket input and output
     */

    private static void startClient() throws IOException {
        System.out.println("[0] Connecting to main server...");
        clientSocket.connect(new InetSocketAddress("127.0.0.1", 8600));

        System.out.println("[1] Connection successful!");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintStream(clientSocket.getOutputStream(), true);
    }

    /**
     * This method waits for the server to send
     * a string, saves it then returns it
     * @return The string sent by the server
     */

    private StringBuilder getResponse() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while (!in.ready()) ;
            while (in.ready()) {
                stringBuilder.append(in.readLine());
            }
        } catch (IOException e) {
            System.err.println("Error: I/O error.");
            e.printStackTrace();

            fail();
        }

        return stringBuilder;
    }
}