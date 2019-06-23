package PickupPointSystem.DatabaseSystem.Tables;

import PickupPointSystem.LockerSystem.Size;

public class PackageTable {

    private String id;
    private Size size;
    private String customerEmail;

    public PackageTable(String id, double height, double length, double width, String customerEmail ){
        this.size = new Size(height, length, width);
        this.id = id;
        this.customerEmail = customerEmail;
    }

    public String getId() {
        return id;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }

    public Size getSize() {
        return size;
    }

    public String toString(){
        return getId()+"\t"+size.toString();
    }


}
