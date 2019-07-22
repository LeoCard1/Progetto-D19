package ServerAndDatabase.Connections;

/**
 * This exception is thrown whenever
 * a user attempts to issue an unknown
 * command to the server
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class ConnectionUnknownException extends RuntimeException {

    /**
     * The constructor. It prints an
     * error string
     */

    public ConnectionUnknownException() {
        System.err.println("Error: Connection unknown.");
    }
}
