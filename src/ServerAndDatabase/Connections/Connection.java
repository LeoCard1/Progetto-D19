package ServerAndDatabase.Connections;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * This interface lets the user manage all
 * the connection types seamlessly
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public interface Connection {

    /**
     * This method performs a specific task
     * on the database depending on the
     * string received by the server
     *
     * @param out Server output
     * @param strTok String received by the server
     * @throws SQLException Input/Output error between client and server
     */

    void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException;
}
