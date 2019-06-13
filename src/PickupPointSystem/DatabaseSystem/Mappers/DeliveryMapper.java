package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.Tables.Delivery;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;


/**
 * @author D19 Group
 * @version 2.0
 */

public class DeliveryMapper implements Mapper {

    private ArrayList<Delivery> cache = new ArrayList<>();

    /**
     * This method returns an ArrayList of deliveries inside the database given the PickupPoint
     * id passed as an argument.
     * @param pipoID
     * @return ArrayList<Delivery>
     */

    @Override
    public ArrayList<Delivery> get(String pipoID) {
        if (!cache.isEmpty()) return cache;

        MainServerConnector server = new MainServerConnector();

        StringTokenizer linesTok = new StringTokenizer(server.getDelivery(pipoID), "\n");
        StringTokenizer singleLineTok;
        String[] elements = new String[6];

        ArrayList<Delivery> deliveries = new ArrayList<>();

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

                Delivery deliveryToAdd = new Delivery(elements[0], dateOfDelivery, boxNumber, elements[5], elements[4]);

                deliveries.add(deliveryToAdd);
                if (cache.size() > 50) cache.remove(0);
                cache.add(deliveryToAdd);
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

    public void removeRowFromPackID(String packID){
        clearCache();
        MainServerConnector server = new MainServerConnector();
        server.removeRowFromPackID(packID);
        server.close();
    }

    /**
     * This method updates the delivery date, the box number and the box password of
     * the given Delivery.
     * @param delivery
     */

    public void update(Delivery delivery){
        clearCache();
        MainServerConnector server = new MainServerConnector();
        server.updateDelivery(delivery.getPackID(), String.valueOf(delivery.getDateOfDelivery().getTime()),
                String.valueOf(delivery.getBoxNumber()), delivery.getBoxPassword());
        server.close();
    }

    public void clearCache() {
        cache.clear();
    }
}
