package DeliveryManSystem.DatabaseSystem.Mappers;

import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;

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
     * database given the deliveryman's
     * ID
     *
     * @param delID The deliveryman's ID
     * @return ArrayList<DeliveryTable> The list of DeliveryTable objects
     * @throws IOException Input/Output error between client and server
     */

    @Override
    public ArrayList<DeliveryTable> get(String delID) throws IOException {

        MainServerConnector server = new MainServerConnector();

        StringTokenizer linesTok = new StringTokenizer(server.getDelivery(delID), "\n");
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

                DeliveryTable deliveryToAdd = new DeliveryTable(elements[0], elements[1], dateOfDelivery, boxNumber, elements[5], elements[4]);

                deliveries.add(deliveryToAdd);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        server.close();
        return deliveries;
    }
}

