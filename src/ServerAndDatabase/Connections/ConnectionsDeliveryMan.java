package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ConnectionsDeliveryMan implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement;

    @Override
    public void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException {
        this.statement = datCon.connectAndReturnStatement();

        String nextToken = strTok.nextToken();

        /*
        deliveryman get delID
         */
        if (nextToken.equalsIgnoreCase("get")) {
            get(strTok, out);
        }
    }

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
