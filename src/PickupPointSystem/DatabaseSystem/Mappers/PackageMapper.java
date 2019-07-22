package PickupPointSystem.DatabaseSystem.Mappers;

import PickupPointSystem.DatabaseSystem.Tables.PackageTable;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * This class returns PackageTable
 * objects from the database
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class PackageMapper implements Mapper {

    /**
     * This method returns a PackageTable
     * object from the database given the
     * package ID
     *
     * @param packID The package ID
     * @return The PackageTable object
     * @throws IOException Input/Output error between client and server
     */

    @Override
    public PackageTable get(String packID) throws IOException {
        MainServerConnector server = new MainServerConnector();

        String packageDimensions = server.packageGet(packID);
        server.close();

        StringTokenizer strTok = new StringTokenizer(packageDimensions.replaceAll("\n", ""));

        double height = Double.parseDouble(strTok.nextToken());
        double length = Double.parseDouble(strTok.nextToken());
        double width = Double.parseDouble(strTok.nextToken());
        String customerEmail = strTok.nextToken();

        return new PackageTable(packID, height, length, width, customerEmail);
    }

    /**
     * This method removes the package from the database
     *
     * @param packID The package ID
     */

    public void remove(String packID) throws IOException {
        MainServerConnector server = new MainServerConnector();
        server.removePackage(packID);
        server.close();
    }
}
