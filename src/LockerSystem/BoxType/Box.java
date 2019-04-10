package LockerSystem.BoxType;

import LockerSystem.Size;
import LockerSystem.Package;
import java.util.Date;

public abstract class Box {

    protected boolean availability;
    protected Size size;
    private Package pacco;
    protected static int code;
    protected Date date;

    public Box(){
        availability = true;
        code++;
    }

    public void addPackage(Package pacco){
        this.pacco = pacco;
        availability = false;
    }

    public void removePackage(){
         availability = true;
    }

}
