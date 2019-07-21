package DeliveryManSystem.DatabaseSystem.Tables;

import DeliveryManSystem.DateHandler;

import java.util.Date;

/**
 * This class represents the 'deliveries' database table
 *
 * @author Gruppo D19
 * @version 1.0.1
 */

public class DeliveryTable {

    private String packID;
    private String pipoID;
    private Date dateOfDelivery;
    private int boxNumber;
    private String boxPassword;
    private String delID;

    /**
     * The constructor. It sets the package id, the pickup
     * point id, the delivery date, the box number, the box
     * password and the deliveryman's id
     *
     * @param packID The package id
     * @param pipoID The ID of the pickup point where the package was delivered
     * @param dateOfDelivery The delivery date
     * @param boxNumber The box number identifying the package in the pickup point
     * @param boxPassword The password of the box in which the pack was inserted
     * @param delID The ID of the deliveryman who made the delivery
     */

    public DeliveryTable(String packID, String pipoID, Date dateOfDelivery, int boxNumber, String boxPassword, String delID) {
        this.packID = packID;
        this.pipoID = pipoID;
        this.dateOfDelivery = dateOfDelivery;
        this.boxNumber = boxNumber;
        this.boxPassword = boxPassword;
        this.delID = delID;
    }

    /**
     * This method returns the package ID
     *
     * @return The package ID
     */

    public String getPackID() {
        return packID;
    }

    /**
     * This method returns the pickup point ID
     *
     * @return The pickup point ID
     */

    public String getPipoID() {
        return pipoID;
    }

    /**
     * This method checks if the delivery was made
     *
     * @return True if the delivery was made, else it's false
     */

    public boolean wasMade() {
        if (dateOfDelivery != null) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if 3 days or more have passed
     * since the delivery was made
     *
     * @return True if 3 or more days have passed, else it's false
     */

    public boolean hasPackDeliveredForThreeDays(){
        DateHandler dateHandler = new DateHandler();
        if(dateHandler.differenceInDays(new Date(),dateOfDelivery)>3){
            return true;
        }
        return false;
    }
}
