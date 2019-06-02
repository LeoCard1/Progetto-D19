package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.Tables.Package;
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
            ResultSet res = stm.executeQuery("select * from packages where id = \""+ packID + "\"");
            while(res.next()){
                double height = res.getDouble("height");
                double length = res.getDouble("length");
                double width = res.getDouble("width");
                return new Package(packID,height,length,width);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method removes the package from the database.
     * @param packID
     */

    public void remove(String packID){
        try {
            ResultSet res = stm.executeQuery("select * from packages where id = \""+ packID + "\"");
            while(res.next()){
                res.deleteRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
