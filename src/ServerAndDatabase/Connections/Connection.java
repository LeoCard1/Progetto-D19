package ServerAndDatabase.Connections;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * This interface lets you manage the
 * various connection types seamlessly
 * @author Gruppo D19
 * @version 1.0.0
 */

public interface Connection {
    void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException;
}
