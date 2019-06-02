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
     * This method returns an ArrayList of deliveries inside the database given the PickupPoint
     * id passed as an argument.
     * @param pipoID
     * @return ArrayList<Delivery>
     */

    @Override
    public ArrayList<Delivery> get(String pipoID) {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        try {
            ResultSet res = stm.executeQuery("select * from deliveries where pickuppoint_id = \""+pipoID+"\"");
            while (res.next()) {
                String packID = res.getString("package_id");
                Date dateOfDelivery = res.getDate("date_of_delivery");
                int boxNumber = res.getInt("box_number");
                String boxPassword = res.getString("box_password");
                String delID = res.getString("deliveryman_id");
                deliveries.add(new Delivery(packID, dateOfDelivery, boxNumber, boxPassword, delID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    /**
     * This method removes the row containing the given pack id.
     * @param packID
     */

    public void removeRowFromPackID(String packID){
        try {
            ResultSet res = stm.executeQuery("select * from deliveries where package_id = \""+ packID +"\"");
            while(res.next()){
                res.deleteRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates the delivery date, the box number and the box password of
     * the given Delivery.
     * @param delivery
     */

    public void update(Delivery delivery){
        try {
            ResultSet res = stm.executeQuery("select * from deliveries where package_id = \"" + delivery.getPackID() +"\"");
            while(res.next()){
                res.updateDate("date_of_delivery", new java.sql.Date(delivery.getDateOfDelivery().getTime()));
                res.updateInt("box_number", delivery.getBoxNumber());
                res.updateString("box_password", delivery.getBoxPassword());
                res.updateRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
