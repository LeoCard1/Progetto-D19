package PickupPointSystem.DatabaseSystem.Tables;

import PickupPointSystem.DateHandler;

import java.util.Date;

/**
 * This class represents the 'deliveries' database table
 *
 * @author Gruppo D19
 * @version 1.0.0
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

    public DeliveryTable(String packID, String pipoID, Date dateOfDelivery, int boxNumber, String boxPassword, String delID){
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

    public String getPackID(){
        return packID;
    }

    /**
     * This method returns the delivery date
     *
     * @return The delivery date
     */

    public Date getDateOfDelivery(){
        return dateOfDelivery;
    }

    /**
     * This method returns the number of the box where the
     * package is stored
     *
     * @return The box number
     */

    public int getBoxNumber(){
        return boxNumber;
    }

    /**
     * This method returns the box password
     *
     * @return The box password
     */

    public String getBoxPassword(){
        return boxPassword;
    }

    /**
     * This method sets the delivery date
     *
     * @param dateOfDelivery The date to be set
     */

    public void setDateOfDelivery(Date dateOfDelivery){
        this.dateOfDelivery = dateOfDelivery;
    }

    /**
     * This method sets the box number
     *
     * @param boxNumber The box number to be set
     */

    public void setBoxNumber(int boxNumber){
        this.boxNumber = boxNumber;
    }

    /**
     * This method sets the box password
     *
     * @param boxPassword The box password to be set
     */

    public void setBoxPassword(String boxPassword) {
        this.boxPassword = boxPassword;
    }

    /**
     * This method checks that the delivery has been entrusted
     * to the delivery man whose ID is passed as an argument
     *
     * @param delID The deliveryman's ID
     * @return Boolean that checks whether the deliveryman's ID is the same as the one specified in the delivery
     */

    public boolean hasDelID(String delID){
        if (this.delID != null && this.delID.equals(delID)) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the box number passed as an
     * argument is the same as the one specified in the
     * delivery
     *
     * @param boxNumber The box number
     * @return Boolean that checks whether the box number is the same as the one specified in the delivery
     */

    public boolean hasBoxNumber(int boxNumber){
        if (this.boxNumber == boxNumber) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the package ID passed as an
     * argument is the same as the one specified in the
     * delivery
     *
     * @param packID The package ID
     * @return Boolean that checks whether the package ID is the same as the one specified in the delivery
     */

    public boolean hasPackage(String packID){
        if (this.packID.equals(packID)) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the delivery was made
     *
     * @return True if the delivery was made, else it's false
     */

    public boolean wasMade(){
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
        if (dateHandler.differenceInDays(new Date(),dateOfDelivery) > 3) {
            return true;
        }
        return false;
    }
}
