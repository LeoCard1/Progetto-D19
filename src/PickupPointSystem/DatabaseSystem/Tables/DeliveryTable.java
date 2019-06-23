package PickupPointSystem.DatabaseSystem.Tables;


import PickupPointSystem.DateHandler;

import java.util.Date;

/**
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

    public DeliveryTable(String packID, String pipoID, Date dateOfDelivery, int boxNumber, String boxPassword, String delID){
        this.packID = packID;
        this.pipoID = pipoID;
        this.dateOfDelivery = dateOfDelivery;
        this.boxNumber = boxNumber;
        this.boxPassword = boxPassword;
        this.delID = delID;
    }

    public String getPackID(){
        return packID;
    }

    public Date getDateOfDelivery(){
        return dateOfDelivery;
    }

    public int getBoxNumber(){
        return boxNumber;
    }

    public String getBoxPassword(){
        return boxPassword;
    }

    public void setDateOfDelivery(Date dateOfDelivery){
        this.dateOfDelivery = dateOfDelivery;
    }

    public void setBoxNumber(int boxNumber){
        this.boxNumber = boxNumber;
    }

    public void setBoxPassword(String boxPassword) {
        this.boxPassword = boxPassword;
    }

    public boolean hasDelID(String delID){
        if(this.delID!=null && this.delID.equals(delID)){
            return true;
        }
        return false;
    }

    public boolean hasBoxNumber(int boxNumber){
        if(this.boxNumber == boxNumber){
            return true;
        }
        return false;
    }

    public boolean hasPackage(String packID){
        if(this.packID.equals(packID)){
            return true;
        }
        return false;
    }

    public boolean wasMade(){
        if(dateOfDelivery!=null){
            return true;
        }
        return false;
    }

    public boolean hasPackDeliveredForThreeDays(){
        DateHandler dateHandler = new DateHandler();
        if(dateHandler.differenceInDays(new Date(),dateOfDelivery)>3){
            return true;
        }
        return false;


    }

}
