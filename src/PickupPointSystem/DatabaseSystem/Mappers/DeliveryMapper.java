package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.DatabaseConnect;
import PickupPointSystem.DatabaseSystem.Tables.Delivery;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class DeliveryMapper implements Mapper {

    private Statement stm;

    /**
     * The constructor. Initialize the statement.
     */

    public DeliveryMapper(){
        DatabaseConnect dbc = new DatabaseConnect();
        stm = dbc.connect();
    }

    /**
     * This method returns an ArrayList of deliveries inside the database given the DeliveryMan
     * id passed as an argument.
     * @param delID
     * @return ArrayList<Delivery>
     */

    @Override
    public ArrayList<Delivery> get(String delID) {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        try {
            ResultSet res = stm.executeQuery("select * from deliveries");
            while (res.next()) {
                if(res.getString("deliveryman_id").equals(delID) && res.getInt("box_number")!=0){
                    String packID = res.getString("package_id");
                    Date dateOfDelivery = res.getDate("date_of_delivery");
                    Date dateOfArrival = res.getDate("date_of_arrival");
                    int boxNumber = res.getInt("box_number");
                    deliveries.add(new Delivery(packID, dateOfDelivery, dateOfArrival, boxNumber, delID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveries;
    }
    /*
    public ArrayList<Delivery> getFromPickupPointID(String pipoID){

    }
    */

}
