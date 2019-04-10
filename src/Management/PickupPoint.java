package Management;

import LockerSystem.BoxType.Box;

import java.util.ArrayList;

public class PickupPoint {
    private String id; //Codice identificativo punto di ritiro?
    private ArrayList<Box>listaBox; // Box attualmente presenti nel Punto di ritiro
    private int numeroBoxUsati; // Da utilizzare nelle statistiche

    public PickupPoint(String id){
        this.id = id;
        listaBox = new ArrayList<>();
        numeroBoxUsati = 0;
    }

    public PickupPoint(int numeroBoxIniziali , String id){
        this.id = id;
        listaBox = new ArrayList<>(numeroBoxIniziali);
        numeroBoxUsati = 0;
    }

}
