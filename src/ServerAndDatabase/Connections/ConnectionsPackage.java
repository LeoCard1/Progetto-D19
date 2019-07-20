package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ConnectionsPackage implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement = datCon.connectAndReturnStatement();

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

    private void remove(StringTokenizer strTok) throws SQLException {
        ResultSet res = statement.executeQuery("select * from packages where id = \""
                + strTok.nextToken() + "\"");

        while(res.next()){
            res.deleteRow();
        }
    }
}
