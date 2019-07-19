package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ConnectionsDelivery implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement = datCon.connectAndReturnStatement();

    @Override
    public void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException {

        String nextToken = strTok.nextToken();

        /*
        delivery getfrompipoid piPoName
         */
        if (nextToken.equalsIgnoreCase("getfrompipoid")) {
            getFromPipoID(strTok, out);
        }

         /*
        delivery getfromdelid DeliveryManName
         */
        if (nextToken.equalsIgnoreCase("getfromdelid")) {
            getFromDelManID(strTok, out);
        }

        /*
        delivery removerowfrompackid packID
         */
        if (nextToken.equalsIgnoreCase("removerowfrompackid")) {
            removeRowFromPackID(strTok);
        }

        /*
        delivery refresh packID dateOfDelivery boxNumber boxPassword
         */
        if (nextToken.equalsIgnoreCase("refresh")) {
            updateDelivery(strTok);
        }
    }

    private void getFromPipoID(StringTokenizer strTok, PrintStream out) throws SQLException {

        ResultSet res = statement.executeQuery("select * from deliveries where pickuppoint_id = \""
                + strTok.nextToken() + "\"");

        sendDelivery(res, out);
    }

    private void getFromDelManID(StringTokenizer strTok, PrintStream out) throws SQLException {

        ResultSet res = statement.executeQuery("select * from deliveries where deliveryman_id = \""
                + strTok.nextToken() + "\"");

        sendDelivery(res, out);
    }

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
