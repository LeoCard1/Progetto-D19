package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ConnectionsPickupPoint implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement;

    @Override
    public void manageConnection(PrintStream out, StringTokenizer strTok) throws SQLException {
        this.statement = datCon.connectAndReturnStatement();

        String nextToken = strTok.nextToken();

        /*
        pickuppoint get piPoName
         */
        if (nextToken.equalsIgnoreCase("get")) {
            get(strTok, out);
        }
    }

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
}
