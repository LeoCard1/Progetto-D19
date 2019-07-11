package PickupPointSystem.DatabaseSystem.Tables;

/**
 * This class is the representation of the delivery man database table
 * @author Andrea Stella
 * @version 1.0
 */

public class DeliveryManTable {

    private String delID;
    private String password;

    /**
     * The constructor. Initializes id and password
     * @param delID the delivery man id
     * @param password the delivery man password
     */

    public DeliveryManTable(String delID, String password){
        this.delID = delID;
        this.password = password;
    }

    /**
     * @return the delivery man password
     */

    public String getPassword(){
        return password;
    }


}
