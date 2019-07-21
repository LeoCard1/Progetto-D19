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
 * This class returns DeliveryTable
 * objects from the database
 *
 * @author Gruppo D19
 * @version 2.0.0
 */

public class DeliveryMapper implements Mapper {

    /**
     * This method returns a list of
     * DeliveryTable objects from the
     * database given the pickup point
     * id
     *
     * @param pipoID The pickup point ID
     * @return ArrayList<DeliveryTable> The list of DeliveryTable objects
     * @throws IOException Input/Output error between client and server
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
     * This method removes the row
     * from the database containing
     * the given pack ID
     *
     * @param packID The ID of the package to be removed
     */

    public void removeRowFromPackID(String packID) throws IOException {
        MainServerConnector server = new MainServerConnector();
        server.removeRowFromPackID(packID);
        server.close();
    }

    /**
     * This method updates the delivery
     * date, the box number and the box
     * password of the given DeliveryTable
     *
     * @param delivery The delivery to be updated
     */

    public void update(DeliveryTable delivery) throws IOException {
        MainServerConnector server = new MainServerConnector();
        server.updateDelivery(delivery.getPackID(), String.valueOf(delivery.getDateOfDelivery().getTime()),
                String.valueOf(delivery.getBoxNumber()), delivery.getBoxPassword());
        server.close();
    }
}
