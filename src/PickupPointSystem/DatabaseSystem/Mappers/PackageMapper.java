package PickupPointSystem.DatabaseSystem.Mappers;

import LockerSystem.Package;
import PickupPointSystem.DatabaseSystem.DatabaseConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class PackageMapper implements Mapper {

    Statement stm;

    /**
     * The constructor. Initialize the statement.
     */

    public PackageMapper(){
        DatabaseConnect dbc = new DatabaseConnect();
        stm = dbc.connect();
    }

    /**
     * This method returns the internal Package to the database given the packID
     * passed as an argument.
     * @param packID
     * @return Package
     */

    @Override
    public Package get(String packID) {
        try {
            ResultSet res = stm.executeQuery("select * from packages");
            while(res.next()){
                if(res.getString("id").equals(packID)){
                    double height = Double.parseDouble(res.getString("height"));
                    double lenght = Double.parseDouble(res.getString("lenght"));
                    double width = Double.parseDouble(res.getString("width"));
                    return new Package(packID,height,lenght,width);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
