package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/**
 * This class manages the requests
 * pertaining to the 'packages' table
 *
 * @author Gruppo D19
 * @version 1.0.1
 */

public class ConnectionsPackage implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement = datCon.connectAndReturnStatement();

    /**
     * This method performs a specific task
     * on the database depending on the
     * string received by the server.
     * It performs queries and updates on
     * the 'deliveries' table
     *
     * @param out Server output
     * @param strTok String received by the server
     * @throws SQLException Input/Output error between client and server
     */

    @Override
    public void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException {

        String nextToken = strTok.nextToken();

        /*
        package get packID
         */
        if (nextToken.equals("get")) {
            get(strTok, out);
        }

        /*
        package remove packID
         */
        if (nextToken.equals("remove")) {
            remove(strTok);
        }
    }

    /**
     * This method returns the contents of the
     * 'packages' table related to the specified
     * package
     *
     * @param strTok String received by the server where the package ID is specified
     * @param out Server output
     * @throws SQLException Input/Output error between client and server
     */

    private void get(StringTokenizer strTok, PrintStream out) throws SQLException {
        ResultSet res = statement.executeQuery("select * from packages where id = \""
                + strTok.nextToken() + "\"");

        while(res.next()){
            double height = res.getDouble("height");
            double length = res.getDouble("length");
            double width = res.getDouble("width");
            String customerEmail = res.getString("customer_email");

            out.println(height + " " + length + " " + width + " " +customerEmail);
        }
    }

    /**
     * This method removes the specified package
     * from the database
     *
     * @param strTok String received by the server where the package ID is specified
     * @throws SQLException Input/Output error between client and server
     */

    private void remove(StringTokenizer strTok) throws SQLException {
        ResultSet res = statement.executeQuery("select * from packages where id = \""
                + strTok.nextToken() + "\"");

        while(res.next()){
            res.deleteRow();
        }
    }
}
