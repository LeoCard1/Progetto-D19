package ServerAndDatabase;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.Assert.*;

public class MainServerTest {
    private static Socket clientSocket = new Socket();
    private static BufferedReader in;
    private static PrintStream out;

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

    @org.junit.AfterClass
    public static void afterTest() {
        out.println("close");
    }





    @org.junit.Test
    public void getErrorResponse() {
        out.println("delivery getfrompipoid test");

        StringBuilder response = getResponse();
        assertEquals(response.toString().replaceAll("\n", ""), "");
    }

    @org.junit.Test
    public void getDeliverymanPassword() {
        out.println("deliveryman get test");

        StringBuilder response = getResponse();
        assertEquals(response.toString().replaceAll("\n", ""), "test");
    }





    private static void startClient() throws IOException {
        System.out.println("[0] Connecting to main server...");
        clientSocket.connect(new InetSocketAddress("127.0.0.1", 8600));

        System.out.println("[1] Connection successful!");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintStream(clientSocket.getOutputStream(), true);
    }

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