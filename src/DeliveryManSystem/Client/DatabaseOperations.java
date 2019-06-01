package DeliveryManSystem.Client;

import DeliveryManSystem.DeliveryMan;
import DeliveryManSystem.Exceptions.UsernameOrPasswordException;

import java.sql.*;

public class DatabaseOperations {
    private String url = "jdbc:mysql://remotemysql.com:3306/QaZzMlKPoa";
    private String delManID;
    private String delManPassword;
    private String dbUsername = "QaZzMlKPoa";
    private String dbPassword = "35sMfdz9D4";
    private DeliveryMan deliveryMan;

    public DatabaseOperations(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public void synchronizePackages(String id, String password) {
        delManID = id;
        delManPassword = password;

        try {
            Statement statement = connect();
            logIn(statement);
        } catch (ClassNotFoundException | SQLException | UsernameOrPasswordException e) {
            e.printStackTrace();
        }
    }

    private Statement connect() throws ClassNotFoundException, SQLException {
       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
       return connection.createStatement();
    }

    private void logIn(Statement statement) throws SQLException {
        ResultSet res = statement.executeQuery("select * " +
                "from deliverymen " +
                "where id = \"" + delManID + "\" and password = \"" + delManPassword + "\"");

        if (!res.next()) throw new UsernameOrPasswordException();
    }
}
