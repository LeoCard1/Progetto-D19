package ClientSystem;

import LockerSystem.Package;
import ObserverPattern.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class DeliveryMan implements Observer {
    private String id; // id univoco che identifica un fattorino
    private ArrayList<Package> packageList;
    private int packCounter; // pacchi totali consegnati. Utilizzo nei dati statistici


    public DeliveryMan(String id){
        this.id = id;
        packageList = new ArrayList<>();
        packCounter = 0;
    }


    public void addPackage(Package pack){
        packageList.add(pack);
        packCounter++;
    }

    public void removePackage(Package pack){
        packageList.remove(pack);
    }

    public int getPackCounter() {
        return packCounter;
    }

    public ArrayList<Package> getListaPacchi() {
        return packageList;
    }

    @Override
    public void update() {
        packCounter += packageList.size();
        packageList.clear();
    }
}
