package ServerAndDatabase.Connections;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

public interface Connection {
    void manageConnection(BufferedReader in, PrintStream out, Socket client);
}
