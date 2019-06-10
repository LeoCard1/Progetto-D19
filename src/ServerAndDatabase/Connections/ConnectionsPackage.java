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

public class ConnectionsPackage implements Connection {
    private Statement statement;

    @Override
    public boolean manageConnection(BufferedReader in, PrintStream out, Socket client, StringTokenizer strTok) throws SQLException {
        DatabaseConnector datCon = new DatabaseConnector();
        this.statement = datCon.connectAndReturnStatement();

        String nextToken = strTok.nextToken();

        /*
        package get packID
         */
        if (nextToken.equalsIgnoreCase("get")) {
            get(strTok, out);
            return true;
        }

        /*
        package remove packID
         */
        if (nextToken.equalsIgnoreCase("remove")) {
            remove(strTok);
            return true;
        }

        /*
        package close
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
        ResultSet res = statement.executeQuery("select * from packages where id = \""
                + strTok.nextToken() + "\"");

        while(res.next()){
            double height = res.getDouble("height");
            double length = res.getDouble("length");
            double width = res.getDouble("width");

            out.println(height + " " + length + " " + width);
        }
    }

    private void remove(StringTokenizer strTok) throws SQLException {
        ResultSet res = statement.executeQuery("select * from packages where id = \""
                + strTok.nextToken() + "\"");

        while(res.next()){
            res.deleteRow();
        }
    }

    private void close(BufferedReader in, PrintStream out, Socket client) throws IOException {
        in.close();
        out.close();
        client.close();
    }
}
