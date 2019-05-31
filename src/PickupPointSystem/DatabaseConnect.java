package PickupPointSystem;

import java.sql.*;


public class DatabaseConnect {


    public DatabaseConnect(){
        String url = "jdbc:mysql://remotemysql.com:3306/QaZzMlKPoa";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url,"QaZzMlKPoa","35sMfdz9D4");
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from deliverymen");
            while(rs.next()){
                System.out.println(rs.getString("id")+"n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    
}
