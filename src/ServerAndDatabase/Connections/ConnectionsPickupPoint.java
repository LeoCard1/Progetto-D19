package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ConnectionsPickupPoint implements Connection {

    @Override
    public boolean manageConnection(BufferedReader in, PrintStream out, Socket client, StringTokenizer strTok) throws SQLException {
        DatabaseConnector datCon = new DatabaseConnector();
        Statement statement = datCon.connectAndReturnStatement();

        String nextToken = strTok.nextToken();
        StringBuffer strBuf = new StringBuffer();

        /*
        pickuppoint get piPoName
         */
        if (nextToken.equalsIgnoreCase("get")) {
            ResultSet res = statement.executeQuery("select * from deliveries where pickuppoint_id = \""
                    + strTok.nextToken() + "\"");
            while (res.next()) {
                for (int i = 1; i < 7; i++) {
                    strBuf.append(res.getString(i) + ".");
                }
                strBuf.append("\n");
            }
            out.println(strBuf);
            return true;
        }

        /*
        pickuppoint removerowfrompackid packID
         */
        if (nextToken.equalsIgnoreCase("removerowfrompackid")) {
            ResultSet res = statement.executeQuery("select * from deliveries where package_id = \""
                    + strTok.nextToken() + "\"");
            while (res.next()) {
                res.deleteRow();
            }
            out.println(strBuf);
            return true;
        }

        /*
        pickuppoint update packID dateOfDelivery boxNumber boxPassword
         */
        if (nextToken.equalsIgnoreCase("update")) {
            ResultSet res = statement.executeQuery("select * from deliveries where package_id = \""
                    + strTok.nextToken() +"\"");
            while (res.next()) {
                res.updateDate("date_of_delivery", new java.sql.Date(Long.parseLong(strTok.nextToken())));
                res.updateInt("box_number", Integer.parseInt(strTok.nextToken()));
                res.updateString("box_password", strTok.nextToken());
                res.updateRow();
            }
        }
        /*{
            ResultSet res = stm.executeQuery("select * from deliveries where package_id = \"" + delivery.getPackID() +"\"");
            while(res.next()){
                res.updateDate("date_of_delivery", new java.sql.Date(delivery.getDateOfDelivery().getTime()));
                res.updateInt("box_number", delivery.getBoxNumber());
                res.updateString("box_password", delivery.getBoxPassword());
                res.updateRow();
            }*/

        /*
        pickuppoint close
         */
        if (nextToken.equalsIgnoreCase("close")) {
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }
}
