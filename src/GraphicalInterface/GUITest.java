package GraphicalInterface;

import LockerSystem.Package;
import Management.PickupPoint;

import java.io.IOException;

public class GUITest {
    public static void main(String[] args) throws IOException {
        PickupPoint piPo = new PickupPoint("piPo", 24, 16 , 10);

        Package pack1 = new Package("pack1", 72,24,65);
        Package pack2 = new Package("pack2", 20, 15, 29);
        Package pack3 = new Package("pack3", 55,40,36);
        Package pack4 = new Package("pack4", 37, 60, 46);
        Package pack5 = new Package("pack1", 43,24,65);
        Package pack6 = new Package("pack2", 20, 32, 49);
        Package pack7 = new Package("pack3", 15,20,16);
        Package pack8 = new Package("pack4", 33, 40, 71);

        piPo.addPackage(pack1);
        piPo.addPackage(pack2);
        piPo.addPackage(pack3);
        piPo.addPackage(pack4);
        piPo.addPackage(pack5);
        piPo.addPackage(pack6);
        piPo.addPackage(pack7);
        piPo.addPackage(pack8);

        piPo.removePackage(pack1);

        GraIntMain gui = new GraIntMain(piPo);
    }
}
