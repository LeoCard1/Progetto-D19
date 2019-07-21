package PickupPointSystem.DatabaseSystem.Tables;

/**
 * This class represents the 'deliverymen' database table
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class DeliveryManTable {

    private String delID;
    private String password;

    /**
     * The constructor. It sets the ID and the password
     *
     * @param delID The deliveryman's ID
     * @param password The deliveryman's password
     */

    public DeliveryManTable(String delID, String password){
        this.delID = delID;
        this.password = password;
    }

    /**
     * This method returns the password
     *
     * @return The deliveryman's password
     */

    public String getPassword(){
        return password;
    }
}
