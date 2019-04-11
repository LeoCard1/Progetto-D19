package GraphicalInterface;

import Management.PickupPoint;

public class GUITest {
    public static void main(String[] args) {
        PickupPoint pa = new PickupPoint(60, "A");
        GraIntMain gui = new GraIntMain(pa);
    }
}
