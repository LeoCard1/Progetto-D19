package PickupPointSystem.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public interface ManageConnections {
    public void goConnect(ServerSocket server, Socket client, BufferedReader in, PrintStream out) throws IOException;
}
