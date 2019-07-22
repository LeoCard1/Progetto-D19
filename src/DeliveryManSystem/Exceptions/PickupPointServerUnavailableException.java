package DeliveryManSystem.Exceptions;

public class PickupPointServerUnavailableException extends RuntimeException {

    public PickupPointServerUnavailableException() {
        System.err.println("Error: Impossible to connect to Pickup Point server");
    }
}

