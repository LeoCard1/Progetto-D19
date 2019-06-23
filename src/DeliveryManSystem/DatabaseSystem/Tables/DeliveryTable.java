package DeliveryManSystem.DatabaseSystem.Tables;


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

}
