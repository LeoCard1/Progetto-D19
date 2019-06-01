package PickupPointSystem.DatabaseSystem.Tables;

import PickupPointSystem.LockerSystem.Size;

public class Package {

    private String id;
    private Size size;

    public Package(String id, double height, double length, double width ){
        this.size = new Size(height, length, width);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }

    public String toString(){
        return getId()+"\t"+size.toString();
    }


}
