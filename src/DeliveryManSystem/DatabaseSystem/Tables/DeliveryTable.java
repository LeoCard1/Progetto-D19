package DeliveryManSystem.DatabaseSystem.Tables;




import DeliveryManSystem.DateHandler;

import java.util.Date;

public class DeliveryTable {

    private String packID;
    private String pipoID;
    private Date dateOfDelivery;
    private int boxNumber;
    private String boxPassword;
    private String delID;

    public DeliveryTable(String packID, String pipoID, Date dateOfDelivery, int boxNumber, String boxPassword, String delID) {

        this.packID = packID;
        this.pipoID = pipoID;
        this.dateOfDelivery = dateOfDelivery;
        this.boxNumber = boxNumber;
        this.boxPassword = boxPassword;
        this.delID = delID;
    }

    public String getPackID() {
        return packID;
    }

    public String getPipoID() {
        return pipoID;
    }

    public boolean hasPipoID(String pipoID) {
        if (this.pipoID != null && this.pipoID.equals(pipoID)) {
            return true;
        }
        return false;
    }

    public boolean wasMade() {
        if (dateOfDelivery != null) {
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
