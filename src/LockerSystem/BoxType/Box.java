package LockerSystem.BoxType;

import LockerSystem.Size;
import LockerSystem.Package;
import java.util.Date;

public abstract class Box {

    protected boolean availability;
    protected Size size;
    private Package pack;
    protected static int code;
    private Date date;

    public Box(){
        availability = true;
        pack = null;
        code++;
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

}
