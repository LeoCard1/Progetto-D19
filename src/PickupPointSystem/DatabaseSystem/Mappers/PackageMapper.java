package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.Tables.Package;
import PickupPointSystem.DatabaseSystem.DatabaseConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class PackageMapper implements Mapper {

    /*Statement stm;*/

    /**
     * The constructor. Initialize the statement.
     */

    /*public PackageMapper(){
        DatabaseConnect dbc = new DatabaseConnect();
        stm = dbc.connect();
    }*/

    /**
     * This method returns the internal Package to the database given the packID
     * passed as an argument.
     * @param packID
     * @return Package
     */

    @Override
    public Package get(String packID) {
        MainServerConnector server = new MainServerConnector();

        String packageDimensions = server.packageGet(packID);
        server.close();

        StringTokenizer strTok = new StringTokenizer(packageDimensions.replaceAll("\n", ""));

        double height = Double.parseDouble(strTok.nextToken());
        double length = Double.parseDouble(strTok.nextToken());
        double width = Double.parseDouble(strTok.nextToken());
        String customerEmail = strTok.nextToken();

        return new Package(packID, height, length, width, customerEmail);

        /*try {
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
        */
    }

    /**
     * This method removes the package from the database.
     * @param packID
     */

    public void remove(String packID){
        MainServerConnector server = new MainServerConnector();
        server.removePackage(packID);
        server.close();

        /*try {
            ResultSet res = stm.executeQuery("select * from packages where id = \""+ packID + "\"");
            while(res.next()){
                res.deleteRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }


}
