package LockerSystem;

public class Package {
    private String id; // Codice identificativo di un pacco
    private Size size; // Dimensione del pacco

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


}
