package ServerAndDatabase;

/**
 * This main starts the server
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class ServerMain {

    /**
     * Main class
     *
     * @param args Potential arguments
     */

    public static void main(String[] args) {
        MainServer server = new MainServer();
        server.start();
    }
}
