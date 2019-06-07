package ServerAndDatabase.Connections;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.StringTokenizer;

public interface Connection {
    boolean manageConnection(BufferedReader in, PrintStream out, Socket client, StringTokenizer strTok) throws SQLException;
}
