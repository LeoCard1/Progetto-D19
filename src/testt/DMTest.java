import LockerSystem.DeliveryMan;
import LockerSystem.Package;
import LockerSystem.Size;
//import org.junit.jupiter.api.Test;

public class DMTest {
    DeliveryMan deliveryMan = new DeliveryMan("ciao");
    Package aPackage = new Package("pacco1", new Size(5,5,5));

    //@Test
    public void addPackageTest() {
        deliveryMan.addPackage(aPackage);
    }

    //@Test
    public void removePackageTest() {
        deliveryMan.removePackage(aPackage);
    }


}
