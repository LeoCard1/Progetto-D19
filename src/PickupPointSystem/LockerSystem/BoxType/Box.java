package PickupPointSystem.LockerSystem.BoxType;

import PickupPointSystem.LockerSystem.Size;
import PickupPointSystem.DatabaseSystem.Tables.Package;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public abstract class Box  implements Comparable {

    private boolean availability;
    protected Size size;
    private Package pack;
    private int boxNumber;
    private static int numBox;
    private Date date;

    public Box(){
        availability = true;
        numBox ++;
        boxNumber = numBox;
    }

    public boolean isAvailable(){
        return availability;
    }

    public Size getSize(){
        return size;
    }

    public int getBoxNumber(){
        return boxNumber;
    }

    public Package getPack(){
        return pack;
    }

    public String getStringDate(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd-MM-yyyy");
        return sdf.format(date);
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void addPackage(Package pack){
        availability = false;
        this.pack = pack;
    }

    public void removePackage(){
        availability = true;
        pack = null;
        date = null;
    }

    public int compareTo(Object o){
        Box boxObject = (Box)o;
        return size.compareTo(boxObject.getSize());
    }

    public String toString(){
        String s = "" + boxNumber;
        if(!isAvailable()){
            s += " \t" + pack.getId() + "\t" + getStringDate();
        }
        return s;
    }

    public String generateBoxPassword() {
        String[] division = toString().split("\t");
        division[2] = division[2].replaceAll("\\D","");

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String password = new String();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int n = rand.nextInt(25);
            char c = letters.charAt(n);
            password = password + c;
        }
        password = password + division[0] + division[2];
        return password.replaceAll("\\s+","");
    }

}
