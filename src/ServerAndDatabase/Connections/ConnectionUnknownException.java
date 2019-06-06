package ServerAndDatabase.Connections;

public class ConnectionUnknownException extends RuntimeException {
    public ConnectionUnknownException() {
        System.err.println("Error: Connection unknown.");
    }
}
