package ClientSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    BufferedReader in = null;
    PrintStream out = null;
    Socket socket = null;
    DeliveryMan deliveryMan;

    public Client(DeliveryMan deliveryMan){
        this.deliveryMan = deliveryMan;
    }

    public void connect() throws IOException {
        socket = new Socket("localhost", 8000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream(), true);
        send(deliveryMan.packageListToString());
        read();
        in.close();
        out.close();
        socket.close();
    }


    public void send(String text) throws IOException {
        out.print(text +"\n");
    }

    public void read() throws IOException {
        String message = "";
        while(!in.ready()){
            
        }
        while(in.ready()){
            message+= in.readLine();
        }
        System.out.println(message);
    }
}
