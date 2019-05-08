package LockerSystem.BoxType;

import LockerSystem.Size;
import LockerSystem.Package;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Box  implements Comparable {

    protected boolean availability;
    protected Size size;
    private Package pack;
    protected int code;
    protected static int numBox;
    private Date date;

    public Box(){
        availability = true;
        numBox ++;
        code = numBox;
    }

    public boolean isAvailable(){
        return availability;
    }

    public Size getSize(){
        return size;
    }

    public int getCode(){
        return code;
    }

    public Package getPack(){
        return pack;
    }

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd-MM-yyyy");
        return sdf.format(date);
    }

    public void addPackage(Package pack){
        availability = false;
        this.pack = pack;
        date = new Date();
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
        String s = "" + code;
        if(!isAvailable()){
            s += " \t" + pack.getId() + "\t" + getDate();
        }
        return s;
    }

}
