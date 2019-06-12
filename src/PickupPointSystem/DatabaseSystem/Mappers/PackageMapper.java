package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.Tables.Package;

import java.util.StringTokenizer;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class PackageMapper implements Mapper {

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
    }

    /**
     * This method removes the package from the database.
     * @param packID
     */

    public void remove(String packID){
        MainServerConnector server = new MainServerConnector();
        server.removePackage(packID);
        server.close();
    }


}
