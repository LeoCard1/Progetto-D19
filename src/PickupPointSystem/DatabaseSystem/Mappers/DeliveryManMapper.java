package PickupPointSystem.DatabaseSystem.Mappers;


import PickupPointSystem.DatabaseSystem.DatabaseConnect;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class DeliveryManMapper implements Mapper {

    private Statement stm;

    /**
     * The constructor. Initialize the statement.
     */

    public DeliveryManMapper(){
        DatabaseConnect dbc = new DatabaseConnect();
        stm = dbc.connect();
    }

    /**
     * This method returns the internal DeliveryMan to the database given the
     * id passed as an argument.
     * @param delID
     * @return DeliveryMan
     */

    public DeliveryMan get(String delID){
        try {
            ResultSet res = stm.executeQuery("select id,password from deliverymen where id = \""+ delID +"\"");
            while(res.next()){
<<<<<<< HEAD
                if(res.getString("id").equals(delID)){
                    String password = res.getString("password");
                    stm.close();
                    return new DeliveryMan(delID, password);
                }
=======
                String password = res.getString("password");
                return new DeliveryMan(delID, password);
>>>>>>> 64f1b6bd6e8093033869eb9e5dbb72af8653c8c3
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
