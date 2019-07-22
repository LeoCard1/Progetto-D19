package PickupPointSystem.DatabaseSystem.Tables;

import PickupPointSystem.LockerSystem.Size;

/**
 * This class represents the 'packages' database table
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class PackageTable {

    private String id;
    private Size size;
    private String customerEmail;

    /**
     * The constructor. It sets the ID, the package sizes
     * and the customer's e-mail address
     *
     * @param id The package id
     * @param height The package height
     * @param length The package length
     * @param width The package width
     * @param customerEmail The customer's e-mail address
     */

    public PackageTable(String id, double height, double length, double width, String customerEmail ){
        this.size = new Size(height, length, width);
        this.id = id;
        this.customerEmail = customerEmail;
    }

    /**
     * This method returns the package ID
     *
     * @return The package ID
     */

    public String getId() {
        return id;
    }

    /**
     * This method returns the customer's e-mail address
     *
     * @return The customer's e-mail address
     */

    public String getCustomerEmail(){
        return customerEmail;
    }

    /**
     * This method returns the package size
     *
     * @return The package size
     */

    public Size getSize() {
        return size;
    }

    /**
     * This method returns a string containing the ID and
     * the size of the package
     *
     * @return The string containing the ID and the size of the package
     */

    public String toString(){
        return getId()+"\t"+size.toString();
    }
}
