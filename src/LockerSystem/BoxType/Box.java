package LockerSystem.BoxType;

import LockerSystem.Size;
import LockerSystem.Package;
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

    public boolean isAvailability(){
        return availability;
    }

    public Size getSize(){
        return size;
    }

    public int compareTo(Object o){
        Box boxObject = (Box)o;
        return size.compareTo(boxObject.getSize());
    }

    public String toString(){
        String s = "Codice: "+code;
        if(!isAvailability()){
            s+="  Pacco: " +pack.getId() +"  Data di consegna: " +date;
        }
        return s;
    }
  

}
