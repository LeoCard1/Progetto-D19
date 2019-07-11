package PickupPointSystem.DatabaseSystem.Tables;


import PickupPointSystem.DateHandler;

import java.util.Date;

/**
 * This class is the representation of the delivery database table
 * @author Andrea Stella
 * @version 1.0
 */

public class DeliveryTable {

    private String packID;
    private String pipoID;
    private Date dateOfDelivery;
    private int boxNumber;
    private String boxPassword;
    private String delID;

    /**
     * The constructor. Initializes pack id, pickup point id, delivery date,
     * box number, box password and delivery man id
     * @param packID the pack id
     * @param pipoID the id of the pickup point where the pack was delivered
     * @param dateOfDelivery the delivery date
     * @param boxNumber the number of the box in which the pack was inserted
     * @param boxPassword the password of the box in which the pack was inserted
     * @param delID the id of the delivery man who made the delivery
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
     * @return the pack id
     */

    public String getPackID(){
        return packID;
    }

    /**
     * @return the delivery date
     */

    public Date getDateOfDelivery(){
        return dateOfDelivery;
    }

    /**
     * @return the box number
     */

    public int getBoxNumber(){
        return boxNumber;
    }

    /**
     * @return the box password
     */

    public String getBoxPassword(){
        return boxPassword;
    }

    /**
     * This method sets the delivery date
     * @param dateOfDelivery the date to be set
     */

    public void setDateOfDelivery(Date dateOfDelivery){
        this.dateOfDelivery = dateOfDelivery;
    }

    /**
     * This method sets the box number
     * @param boxNumber the box number to be set
     */

    public void setBoxNumber(int boxNumber){
        this.boxNumber = boxNumber;
    }

    /**
     * This method sets the box password
     * @param boxPassword the box password to be set
     */

    public void setBoxPassword(String boxPassword) {
        this.boxPassword = boxPassword;
    }

    /**
     * This method checks that the delivery has been entrusted to
     * the delivery man whose id is passed as an argument
     * @param delID the delivery man id
     * @return true if the delivery man id is the same as the one
     * in the delivery, else false
     */

    public boolean hasDelID(String delID){
        if(this.delID!=null && this.delID.equals(delID)){
            return true;
        }
        return false;
    }

    /**
     * This method checks if the box number passed as an argument is the
     * same as the one in the delivery
     * @param boxNumber the box number
     * @return true if is the same, else false
     */

    public boolean hasBoxNumber(int boxNumber){
        if(this.boxNumber == boxNumber){
            return true;
        }
        return false;
    }

    /**
     * This method checks if the pack id passed as an argument is the same
     * as the one in the delivery
     * @param packID the pack id
     * @return true if is the same, else false
     */

    public boolean hasPackage(String packID){
        if(this.packID.equals(packID)){
            return true;
        }
        return false;
    }

    /**
     * This method checks if the delivery was made
     * @return true if the delivery was made, else false
     */

    public boolean wasMade(){
        if(dateOfDelivery!=null){
            return true;
        }
        return false;
    }

    /**
     * This method checks if the delivery has been made for more
     * than 3 days
     * @return true if the delivery has been made for more 3 days,
     * else false
     */

    public boolean hasPackDeliveredForThreeDays(){
        DateHandler dateHandler = new DateHandler();
        if(dateHandler.differenceInDays(new Date(),dateOfDelivery)>3){
            return true;
        }
        return false;


    }

}
