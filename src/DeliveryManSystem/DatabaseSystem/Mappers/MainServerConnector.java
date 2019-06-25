package DeliveryManSystem.DatabaseSystem.Mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainServerConnector {
    private Socket client = new Socket();
    private BufferedReader in;
    private PrintStream out;

    public MainServerConnector() {
        startServer();
    }

    private void startServer() {
        try {
            System.out.println("[0] Connecting to main server...");
            client.connect(new InetSocketAddress("127.0.0.1", 8700));

            System.out.println("[1] Connection successful!");
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDelivery(String delID) {
        try {
            return  sendAndWaitForResponse("delivery getfromdelid " + delID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDeliveryMan(String delID) {
        try {
            return sendAndWaitForResponse("deliveryman get " + delID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getPickupPoint(String piPoID) {
        try {
            return sendAndWaitForResponse("pickuppoint get " + piPoID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void close() {
        out.println("close");
    }

    private String sendAndWaitForResponse(String command) throws IOException {
        StringBuffer strBuf = new StringBuffer();
        out.println(command);

        while (!in.ready()) ;

        while (in.ready()) {
            strBuf.append(in.readLine() + "\n");
        }

        return strBuf.toString();
    }
}
