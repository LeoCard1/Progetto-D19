package ServerAndDatabase.Connections;

import ServerAndDatabase.Database.DatabaseConnector;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class ConnectionsPickupPoint implements Connection {
    private DatabaseConnector datCon = new DatabaseConnector();
    private Statement statement = datCon.connectAndReturnStatement();

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

    private void addTestPackages() throws SQLException {
        statement.executeUpdate("INSERT INTO `QaZzMlKPoa`.`packages` (`id`, `width`, `height`, `length`, `customer_email`)" +
                "VALUES ('test', '10', '10', '10', 'random@gmail.com');\n");
        statement.executeUpdate("INSERT INTO `QaZzMlKPoa`.`deliveries` (`package_id`, `pickuppoint_id`, `deliveryman_id`)" +
                "VALUES ('test', 'TEST', 'test');\n");
    }

    private void returnTestPassword(PrintStream out) throws SQLException {
        ResultSet res = statement.executeQuery("select * from deliveries where package_id = 'test'");
        res.next();
        out.println(res.getString("box_password"));
    }
}
