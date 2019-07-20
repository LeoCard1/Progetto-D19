package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/**
 * This class manages the requests
 * pertaining to the 'deliverymen' table
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class ConnectionsDeliveryMan implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement = datCon.connectAndReturnStatement();

    /**
     * This method performs a specific task
     * on the database depending on the
     * string received by the server.
     * It performs queries on the 'deliverymen'
     * table
     *
     * @param out Server output
     * @param strTok String received by the server
     * @throws SQLException Input/Output error between client and server
     */

    @Override
    public void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException {

        String nextToken = strTok.nextToken();

        /*
        deliveryman get delID
         */
        if (nextToken.equals("get")) {
            get(strTok, out);
        }
    }

    /**
     * This method returns the password of the
     * specified deliveryman.
     * It's used to confirm/deny authentications
     *
     * @param strTok String received by the server where the deliveryman ID is specified
     * @param out Server output
     * @throws SQLException Input/Output error between client and server
     */

    private void get(StringTokenizer strTok, PrintStream out) throws SQLException {
        if (!strTok.hasMoreTokens()) {
            out.println("null");
            return;
        }

        ResultSet res = statement.executeQuery("select id, password from deliverymen where id = \""
                + strTok.nextToken() + "\"");

        if (!res.next()) {
            out.println("null");
            return;
        }

        out.println(res.getString("password"));
    }
}
