package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/**
 * This class manages the requests
 * pertaining to the 'deliveries' table
 *
 * @author Gruppo D19
 * @version 1.1.1
 */

public class ConnectionsDelivery implements Connection {
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
        delivery getfrompipoid piPoName
         */
        if (nextToken.equals("getfrompipoid")) {
            getFromPipoID(strTok, out);
        }

         /*
        delivery getfromdelid DeliveryManName
         */
        if (nextToken.equals("getfromdelid")) {
            getFromDelManID(strTok, out);
        }

        /*
        delivery removerowfrompackid packID
         */
        if (nextToken.equals("removerowfrompackid")) {
            removeRowFromPackID(strTok);
        }

        /*
        delivery refresh packID dateOfDelivery boxNumber boxPassword
         */
        if (nextToken.equals("refresh")) {
            updateDelivery(strTok);
        }
    }

    /**
     * This method returns the contents of the
     * 'deliveries' table related to the specified
     * pickup point
     *
     * @param strTok String received by the server where the pickup point ID is specified
     * @param out Server output
     * @throws SQLException Input/Output error between client and server
     */

    private void getFromPipoID(StringTokenizer strTok, PrintStream out) throws SQLException {

        ResultSet res = statement.executeQuery("select * from deliveries where pickuppoint_id = \""
                + strTok.nextToken() + "\"");

        sendDelivery(res, out);
    }

    /**
     * This method returns the contents of the
     * 'deliveries' table related to the specified
     * deliveryman
     *
     * @param strTok String received by the server where the deliveryman ID is specified
     * @param out Server output
     * @throws SQLException Input/Output error between client and server
     */

    private void getFromDelManID(StringTokenizer strTok, PrintStream out) throws SQLException {

        ResultSet res = statement.executeQuery("select * from deliveries where deliveryman_id = \""
                + strTok.nextToken() + "\"");

        sendDelivery(res, out);
    }

    /**
     * This method builds a string out of the
     * results of the specified query
     *
     * @param res The query result
     * @param out Server output
     * @throws SQLException Input/Output error between client and server
     */

    private void sendDelivery(ResultSet res, PrintStream out) throws SQLException {
        StringBuffer strBuf = new StringBuffer();

        while (res.next()) {
            for (int i = 1; i < 7; i++) {
                strBuf.append(res.getString(i) + ".");
            }
            strBuf.append("\n");
        }

        out.println(strBuf);
    }

    /**
     * This method removes a package from the
     * database given its ID
     *
     * @param strTok String received by the server where the package ID is specified
     * @throws SQLException Input/Output error between client and server
     */

    private void removeRowFromPackID(StringTokenizer strTok) throws SQLException {
        String packageID = strTok.nextToken();

        ResultSet res = statement.executeQuery("select * from deliveries where package_id = \""
                    + packageID + "\"");

        while (res.next()) {
            res.deleteRow();
        }

        res = statement.executeQuery("select * from packages where id = \""
                + packageID + "\"");

        while (res.next()) {
            res.deleteRow();
        }
    }

    /**
     * This method updates the database once a
     * package has been delivered to a pickup
     * point
     *
     * @param strTok String received by the server where the package ID is specified
     * @throws SQLException Input/Output error between client and server
     */

    private void updateDelivery(StringTokenizer strTok) throws SQLException {
        ResultSet res = statement.executeQuery("select * from deliveries where package_id = \""
                + strTok.nextToken() +"\"");

        while (res.next()) {
            res.updateDate("date_of_delivery", new java.sql.Date(Long.parseLong(strTok.nextToken())));
            res.updateInt("box_number", Integer.parseInt(strTok.nextToken()));
            res.updateString("box_password", strTok.nextToken());
            res.updateRow();
        }
    }
}
