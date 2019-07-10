package PickupPointSystem.LockerSystem.BoxType;

import PickupPointSystem.DatabaseSystem.Tables.PackageTable;
import PickupPointSystem.LockerSystem.Size;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class represents the box
 * @author Andrea Stella
 * @version 1.0
 */

public abstract class Box  implements Comparable {

    protected Size size;
    private PackageTable pack;
    private int boxNumber;
    private static int numBox;

    /**
     * The constructor.
     */

    public Box(){
        numBox ++;
        boxNumber = numBox;
        pack = null;
    }

    /**
     * If the box is empty the availability is true, else false
     * @return the availability
     */

    public boolean isAvailable(){
        return pack == null;
    }

    public Size getSize(){
        return size;
    }

    public int getBoxNumber(){
        return boxNumber;
    }

    public PackageTable getPack(){
        return pack;
    }

    public void addPackage(PackageTable pack){
        this.pack = pack;
    }

    public void removePackage(){
        pack = null;
    }

    public int compareTo(Object o){
        Box boxObject = (Box)o;
        return size.compareTo(boxObject.getSize());
    }
    
    public String generateBoxPassword() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String password = "";
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int n = rand.nextInt(51);
            char c = letters.charAt(n);
            password += c;
        }

        if (boxNumber < 10) password += "0";
        password += String.valueOf(boxNumber);

        return password;
    }

}
