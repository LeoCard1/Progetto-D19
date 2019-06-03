package PickupPointSystem.DatabaseSystem;

import java.sql.*;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class DatabaseConnect {

    private final String url = "jdbc:mysql://remotemysql.com:3306/QaZzMlKPoa";
    private final String user = "QaZzMlKPoa";
    private final String password = "35sMfdz9D4";
    
    /**
     * This method make the connection with database.
     * @return the Statement of the connection;
     */

    public Statement connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            return stm;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

    

