package DeliveryManSystem.Exceptions;

public class UsernameOrPasswordException extends RuntimeException {

    public UsernameOrPasswordException() {
        System.err.println("Error: invalid username and/or password.");
    }
}
