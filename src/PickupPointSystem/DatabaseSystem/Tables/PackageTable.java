package PickupPointSystem.DatabaseSystem.Tables;

import PickupPointSystem.LockerSystem.Size;

/**
 * This class is the representation of the package database table
 * @author Andrea Stella
 * @version 1.0
 */

public class PackageTable {

    private String id;
    private Size size;
    private String customerEmail;

    /**
     * The constructor. Initializes the size, the id and the customer
     * e-mail
     * @param id the pack id
     * @param height the pack height
     * @param length the pack length
     * @param width the pack width
     * @param customerEmail the customer e-mail
     */

    public PackageTable(String id, double height, double length, double width, String customerEmail ){
        this.size = new Size(height, length, width);
        this.id = id;
        this.customerEmail = customerEmail;
    }

    /**
     * @return pack id
     */

    public String getId() {
        return id;
    }

    /**
     * @return customer e-mail
     */

    public String getCustomerEmail(){
        return customerEmail;
    }

    /**
     * @return the pack size
     */

    public Size getSize() {
        return size;
    }

    /**
     * @return a string containing the id and the size of the pack
     */

    public String toString(){
        return getId()+"\t"+size.toString();
    }


}
