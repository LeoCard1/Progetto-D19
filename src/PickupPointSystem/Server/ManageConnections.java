package PickupPointSystem.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author Gruppo D19
 * @version 1.0.0
 */


public interface ManageConnections {

    /**
     * This method reads the String sent by the client.
     *
     * @param client This is the client who is connected.
     * @param in Input Operation.
     * @param out Output Operation.
     * @throws IOException Exception caused by I/O operations.
     */

     void goConnect(Socket client, BufferedReader in, PrintStream out) throws IOException;
}
