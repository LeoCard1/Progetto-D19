package LockerSystem;

import LockerSystem.BoxType.*;
import Management.PickupPoint;

import java.io.IOException;

public class TestPiPo {

    public static void main(String [] args) throws IOException {
        PickupPoint piPo = new PickupPoint("piPo01");

        Box boxLarge1 = new LargeBox();
        Box boxLarge2 = new LargeBox();
        Box boxLarge3 = new LargeBox();
        Box boxMedium1 = new MediumBox();
        Box boxMedium2 = new MediumBox();
        Box boxMedium3 = new MediumBox();
        Box boxSmall1 = new SmallBox();
        Box boxSmall2 = new SmallBox();
        Box boxSmall3 = new SmallBox();

        piPo.addBox(boxLarge1);
        piPo.addBox(boxLarge2);
        piPo.addBox(boxLarge3);
        piPo.addBox(boxMedium1);
        piPo.addBox(boxMedium2);
        piPo.addBox(boxMedium3);
        piPo.addBox(boxSmall1);
        piPo.addBox(boxSmall2);
        piPo.addBox(boxSmall3);

        Package pack1 = new Package("pack1", 72,24,65);
        Package pack2 = new Package("pack2", 20, 15, 29);
        Package pack3 = new Package("pack3", 55,40,36);
        Package pack4 = new Package("pack4", 37, 62, 46);

        piPo.addPackage(pack1);
        piPo.addPackage(pack2);
        piPo.addPackage(pack3);
        piPo.addPackage(pack4);

        piPo.removePackage(pack3);


    }
}
