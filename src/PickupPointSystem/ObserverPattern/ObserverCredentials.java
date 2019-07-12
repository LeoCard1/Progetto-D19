package PickupPointSystem.ObserverPattern;

/**
 * @author Gruppo D19
 * @version 1.0.0
 */

public interface ObserverCredentials {

    /**
     * This method receives the credentials and updates an object.
     * @param id Deliveryman's ID
     * @param password DeliveryMan's password
     */

    void updateCredentials(String id, String password);
}
