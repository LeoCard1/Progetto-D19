package LockerSystem;

public class Package {
    private String id; // Codice identificativo di un pacco
    private Size size; // Dimensione del pacco

    public Package(String id, Size size){
        this.size = size;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }


}
