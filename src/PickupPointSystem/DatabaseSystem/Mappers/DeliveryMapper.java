package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.Tables.DeliveryTable;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;


/**
 * @author D19 Group
 * @version 2.0
 */

public class DeliveryMapper implements Mapper {

    /**
     * This method returns an ArrayList of deliveries inside the database given the PickupPointTable
     * id passed as an argument.
     * @param pipoID
     * @return ArrayList<DeliveryTable>
     */

    @Override
    public ArrayList<DeliveryTable> get(String pipoID) throws IOException {

        MainServerConnector server = new MainServerConnector();

        StringTokenizer linesTok = new StringTokenizer(server.getDelivery(pipoID), "\n");
        StringTokenizer singleLineTok;
        String[] elements = new String[6];

        ArrayList<DeliveryTable> deliveries = new ArrayList<>();

        while (linesTok.hasMoreTokens()) {
            singleLineTok = new StringTokenizer(linesTok.nextToken(), ".");

            for (int i = 0; singleLineTok.hasMoreTokens(); i++) {
                elements[i] = singleLineTok.nextToken();
                if (elements[i].equalsIgnoreCase("null")) elements[i] = null;
            }

            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfDelivery = null;
                if (elements[2] != null) dateOfDelivery = formatter.parse(elements[2]);

                int boxNumber = 0;
                if (elements[3] != null) boxNumber = Integer.parseInt(elements[3]);

                DeliveryTable deliveryToAdd = new DeliveryTable(elements[0], elements [1], dateOfDelivery, boxNumber, elements[5], elements[4]);

                deliveries.add(deliveryToAdd);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        server.close();
        return deliveries;
    }

    /**
     * This method removes the row containing the given pack id.
     * @param packID
     */

    public void removeRowFromPackID(String packID) throws IOException {
        MainServerConnector server = new MainServerConnector();
        server.removeRowFromPackID(packID);
        server.close();
    }

    /**
     * This method updates the delivery date, the box number and the box password of
     * the given DeliveryTable.
     * @param delivery
     */

    public void update(DeliveryTable delivery) throws IOException {
        MainServerConnector server = new MainServerConnector();
        server.updateDelivery(delivery.getPackID(), String.valueOf(delivery.getDateOfDelivery().getTime()),
                String.valueOf(delivery.getBoxNumber()), delivery.getBoxPassword());
        server.close();
    }

}
