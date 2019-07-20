import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;
import PickupPointSystem.GraphicalInterface.GraIntMain;
import ServerAndDatabase.MainServer;

public class MainAll {
    public static void main(String[] args) {
        MainServer server = new MainServer();
        server.start();

        new GraIntMain("PAV01");

        new Gui();

    }
}
