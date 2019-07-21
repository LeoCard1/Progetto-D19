package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.Tables.PickupPointTable;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * This class returns PickupPointTable
 * objects from the database
 *
 * @author Gruppo D19
 * @version 2.0.0
 */

public class PickupPointMapper implements Mapper {

    /**
     * This method returns a PickupPointTable
     * object from the database given the
     * pickup point ID
     *
     * @param piPoID The pickup point ID
     * @return The PickupPointTable object
     * @throws IOException Input/Output error between client and server
     */

    @Override
    public PickupPointTable get(String piPoID) throws IOException {
        MainServerConnector server = new MainServerConnector();

        StringTokenizer strTok = new StringTokenizer(server.pickupPointGet(piPoID).replace("\n", ""), ":");
        server.close();

        if (!strTok.hasMoreTokens()) return null;

        String id = strTok.nextToken();
        String location = strTok.nextToken();
        String ip = strTok.nextToken();
        String small_boxes = strTok.nextToken();
        String medium_boxes = strTok.nextToken();
        String large_boxes = strTok.nextToken();
        String address = strTok.nextToken();

        return new PickupPointTable(id, location, ip, small_boxes, medium_boxes, large_boxes, address);
    }
}
