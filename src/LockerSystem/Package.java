package LockerSystem;

public class Package {
    private String id; // Codice identificativo di un pacco
    private Size dimension; // Dimensione del pacco

    public Package(String id, Size dimension){
        this.dimension = dimension;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Size getDimension() {
        return dimension;
    }
}
