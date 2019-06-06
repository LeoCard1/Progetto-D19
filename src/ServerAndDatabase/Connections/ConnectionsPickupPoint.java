package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ConnectionsPickupPoint implements Connection {

    @Override
    public void manageConnection(BufferedReader in, PrintStream out, Socket client, StringTokenizer strTok) throws SQLException {
        DatabaseConnector datCon = new DatabaseConnector();
        Statement statement = datCon.connectAndReturnStatement();

        StringBuffer strBuf = new StringBuffer();

        /*
        pickuppoint get piPoName
         */
        if (strTok.nextToken().equalsIgnoreCase("get")) {
            ResultSet res = statement.executeQuery("select * from deliveries where pickuppoint_id = \""
                    + strTok.nextToken() + "\"");
            while (res.next()) {
                for (int i = 1; i < 7; i++) {
                    strBuf.append(res.getString(i) + ".");
                }
                strBuf.append("\n");
            }
        }

        out.println(strBuf);
    }
}
