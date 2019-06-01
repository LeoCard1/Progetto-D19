package PickupPointSystem.DatabaseSystem.Tables;

public class DeliveryMan {
    private String delID;
    private String password;

    public DeliveryMan(String delID, String password){
        this.delID = delID;
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
