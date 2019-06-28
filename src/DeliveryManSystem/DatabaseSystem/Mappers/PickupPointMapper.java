package DeliveryManSystem.DatabaseSystem.Mappers;


import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;

import java.io.IOException;
import java.util.StringTokenizer;

public class PickupPointMapper implements Mapper {
    @Override
    public PickupPointTable get(String piPoID) throws IOException {

        MainServerConnector server = new MainServerConnector();

        StringTokenizer strTok = new StringTokenizer(server.getPickupPoint(piPoID).replace("\n", ""), ":");
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
