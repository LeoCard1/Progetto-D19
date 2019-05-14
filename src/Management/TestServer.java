package Management;

import ClientSystem.Client;
import ClientSystem.DeliveryMan;
import LockerSystem.Package;

import java.io.IOException;

public class TestServer {
    public static void main(String[] args) throws IOException {
        PickupPoint p = new PickupPoint("piPo1", 10, 12, 20);
        DeliveryMan del = new DeliveryMan("Dev");
        del.addPackage(new Package("pipo2",10,12,13));
        del.addPackage(new Package("pipo7",15,12,17));
        Server s = new Server(p);
        s.start();
        Client c = new Client(del);
        c.connect();


    }



}
