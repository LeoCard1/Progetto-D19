import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;
import PickupPointSystem.GraphicalInterface.GraIntMain;
import ServerAndDatabase.MainServer;

/**
 * This main starts the server, the
 * pickup point and the deliveryman's
 * PDA
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class MainAll {

    /**
     * Main class
     *
     * @param args Potential arguments
     */

    public static void main(String[] args) {
        MainServer server = new MainServer();
        server.start();

        new GraIntMain("PAV01");

        new Gui();
    }
}
