package LockerSystem;

public class Package {
    private String id; // Codice identificativo di un pacco
    private Size dimension; // Dimensione del pacco
    Package(String id , Size dimension){
        this.dimension = dimension;
        this.id = id;
    }
}
