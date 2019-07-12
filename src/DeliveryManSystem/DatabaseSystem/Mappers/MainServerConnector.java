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

    public MainServerConnector() throws IOException {
        startServer();
    }

    private void startServer() throws IOException {
        System.out.println("[0] Connecting to main server...");
        client.connect(new InetSocketAddress("127.0.0.1", 8600));
        System.out.println("[1] Connection successful!");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream(), true);
    }

    public String getDelivery(String delID) throws IOException {
        return  sendAndWaitForResponse("delivery getfromdelid " + delID);
    }

    public String getDeliveryMan(String delID) throws IOException {
        return sendAndWaitForResponse("deliveryman get " + delID);
    }

    public String getPickupPoint(String piPoID) throws IOException {
        return sendAndWaitForResponse("pickuppoint get " + piPoID);
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
