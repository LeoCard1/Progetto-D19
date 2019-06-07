package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.DatabaseConnect;
import PickupPointSystem.DatabaseSystem.Tables.Delivery;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;


/**
 * @author Andrea Stella
 * @version 1.0
 */

public class DeliveryMapper implements Mapper {

    private Statement stm;
    private HashMap<String, ArrayList<Delivery>> buffer = new HashMap<>();

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
        if (buffer.containsKey(pipoID)) return buffer.get(pipoID);

        MainServerConnector server = MainServerConnector.getInstance();
        StringTokenizer linesTok = new StringTokenizer(server.get(pipoID), "\n");
        StringTokenizer singleLineTok;
        String[] elements = new String[6];

        ArrayList<Delivery> deliveries = new ArrayList<>();
        /*try {
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
        }*/

        while (linesTok.hasMoreTokens()) {
            singleLineTok = new StringTokenizer(linesTok.nextToken(), ".");

            for (int i = 0; singleLineTok.hasMoreTokens(); i++) {
                elements[i] = singleLineTok.nextToken();
                if (elements[i].equalsIgnoreCase("null")) elements[i] = null;

                System.out.println(elements[i]);
            }

            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfDelivery = null;
                if (elements[2] != null) dateOfDelivery = formatter.parse(elements[2]);

                int boxNumber = 0;
                if (elements[3] != null) boxNumber = Integer.parseInt(elements[3]);

                deliveries.add(new Delivery(elements[0], dateOfDelivery, boxNumber, elements[5], elements[4]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        buffer.put(pipoID, deliveries);
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
