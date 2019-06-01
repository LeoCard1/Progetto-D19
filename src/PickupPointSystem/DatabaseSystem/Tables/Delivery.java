package PickupPointSystem.DatabaseSystem.Tables;

import java.util.Date;

public class Delivery {
    private String packID;
    private Date dateOfDelivery;
    private Date dateOfArrival;
    private int boxNumber;
    private String delID;

    public Delivery(String packID, Date dateOfDelivery, Date dateOfArrival, int boxNumber, String delID){
        this.packID = packID;
        this.dateOfDelivery = dateOfDelivery;
        this.dateOfArrival = dateOfArrival;
        this.boxNumber = boxNumber;
        this.delID = delID;
    }

    public String getPackID(){
        return packID;
    }
}
