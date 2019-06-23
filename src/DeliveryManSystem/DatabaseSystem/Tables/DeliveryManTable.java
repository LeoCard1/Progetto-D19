package DeliveryManSystem.DatabaseSystem.Tables;


/**
 * @author Andrea Stella
 * @version 1.0
 */

public class DeliveryManTable {

    private String delID;
    private String password;

    public DeliveryManTable(String delID, String password){
        this.delID = delID;
        this.password = password;
    }

    public String getPassword(){
        return password;
    }


}
