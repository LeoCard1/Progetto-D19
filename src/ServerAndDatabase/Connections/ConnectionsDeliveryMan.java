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

public class ConnectionsDeliveryMan implements Connection {
    private Statement statement;

    @Override
    public boolean manageConnection(BufferedReader in, PrintStream out, Socket client, StringTokenizer strTok) throws SQLException {
        DatabaseConnector datCon = new DatabaseConnector();
        this.statement = datCon.connectAndReturnStatement();

        String nextToken = strTok.nextToken();
        StringBuffer strBuf = new StringBuffer();

        /*
        deliveryman get delID
         */
        if (nextToken.equalsIgnoreCase("get")) {
            get(strTok, out);
            return true;
        }

        /*
        deliveryman close
         */
        if (nextToken.equalsIgnoreCase("close")) {
            try {
                close(in, out, client);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }

    private void get(StringTokenizer strTok, PrintStream out) throws SQLException {
        ResultSet res = statement.executeQuery("select id, password from deliverymen where id = \""
                + strTok.nextToken() + "\"");

        res.next();
        out.println(res.getString("password"));
    }

    private void close(BufferedReader in, PrintStream out, Socket client) throws IOException {
        in.close();
        out.close();
        client.close();
    }
}
