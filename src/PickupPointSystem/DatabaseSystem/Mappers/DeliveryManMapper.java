package PickupPointSystem.DatabaseSystem.Mappers;


import PickupPointSystem.DatabaseSystem.DatabaseConnect;
import PickupPointSystem.DatabaseSystem.Tables.DeliveryMan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class DeliveryManMapper implements Mapper {

    /*private Statement stm;*/

    /**
     * The constructor. Initialize the statement.
     */

    /*public DeliveryManMapper(){
        DatabaseConnect dbc = new DatabaseConnect();
        stm = dbc.connect();
    }*/

    /**
     * This method returns the internal DeliveryMan to the database given the
     * id passed as an argument.
     * @param delID
     * @return DeliveryMan
     */

    public DeliveryMan get(String delID){
        MainServerConnector server = new MainServerConnector();

        String password = server.deliveryManGet(delID);
        server.close();

        if (password.equalsIgnoreCase("null")) return null;
        return new DeliveryMan(delID, password.replace("\n", ""));

       /* try {
            ResultSet res = stm.executeQuery("select id,password from deliverymen where id = \""+ delID +"\"");
            while(res.next()){
                String password = res.getString("password");
                return new DeliveryMan(delID, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        //return null;
    }

}
