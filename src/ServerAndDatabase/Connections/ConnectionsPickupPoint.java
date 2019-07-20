package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/**
 * This class manages the requests
 * pertaining to the 'pickuppoints' table
 *
 * @author Gruppo D19
 * @version 1.1.1
 */

public class ConnectionsPickupPoint implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement = datCon.connectAndReturnStatement();

    /**
     * This method performs a specific task
     * on the database depending on the
     * string received by the server.
     * It performs queries on the 'pickuppoints'
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
        pickuppoint get piPoName
         */
        if (nextToken.equals("get")) {
            get(strTok, out);
        }

        /*
        pickuppoint test
         */
        if (nextToken.equals("test")) {
            addTestPackages();
        }

        /*
        pickuppoint testpassword
         */
        if (nextToken.equals("testpassword")) {
            returnTestPassword(out);
        }
    }

    /**
     * This method returns the contents of the
     * 'pickuppoints' table related to the specified
     * pickup point
     *
     * @param strTok String received by the server where the pickup point ID is specified
     * @param out Server output
     * @throws SQLException Input/Output error between client and server
     */

    private void get(StringTokenizer strTok, PrintStream out) throws SQLException {
        StringBuffer strBuf = new StringBuffer();

        ResultSet res = statement.executeQuery("select * from pickuppoints where id = \""
                + strTok.nextToken() + "\"");

        while (res.next()) {
            for (int i = 1; i < 8; i++) {
                strBuf.append(res.getString(i) + ":");
            }
        }

        out.println(strBuf);
    }

    /**
     * This method puts the test packages in the
     * database
     *
     * @throws SQLException Input/Output error between client and server
     */

    private void addTestPackages() throws SQLException {
        statement.executeUpdate("INSERT INTO `QaZzMlKPoa`.`packages` (`id`, `width`, `height`, `length`, `customer_email`)" +
                "VALUES ('test', '10', '10', '10', 'random@gmail.com');\n");
        statement.executeUpdate("INSERT INTO `QaZzMlKPoa`.`deliveries` (`package_id`, `pickuppoint_id`, `deliveryman_id`)" +
                "VALUES ('test', 'TEST', 'test');\n");
    }

    /**
     * This method returns the test password
     * from the database (user: test, password:
     * test)
     *
     * @param out Server output
     * @throws SQLException Input/Output error between client and server
     */

    private void returnTestPassword(PrintStream out) throws SQLException {
        ResultSet res = statement.executeQuery("select * from deliveries where package_id = 'test'");
        res.next();
        out.println(res.getString("box_password"));
    }
}
