package ServerAndDatabase.Connections;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.StringTokenizer;

public interface Connection {
    void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException;
}
