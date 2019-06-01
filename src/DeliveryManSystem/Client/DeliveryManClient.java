package DeliveryManSystem.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DeliveryManClient {
    private Socket client;
    private BufferedReader in;
    private PrintStream out;


    public void connectToPickupPoint() {
        try {
            client = new Socket();
            client.connect(new InetSocketAddress("localhost", 8000));

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);

            goConnect();

        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: can't connect to the pickup point.");
        }
    }

    private void goConnect() throws IOException {
        System.out.println("Connection estabilished!");

        while (true) {
            out.println("deliveryman");
            out.println("ANDREA ECAKE91749");
            out.println("close");

            in.close();
            out.close();

            client.close();
        }
    }
}
