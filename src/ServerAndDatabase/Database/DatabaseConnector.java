package ServerAndDatabase.Database;

import java.sql.*;

public class DatabaseConnector {

    private final String url = "jdbc:mysql://remotemysql.com:3306/QaZzMlKPoa";
    private final String user = "QaZzMlKPoa";
    private final String password = "35sMfdz9D4";

    public Statement connectAndReturnStatement() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            return stm;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
