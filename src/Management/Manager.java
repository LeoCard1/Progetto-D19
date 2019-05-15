package Management;

import DeliveryManSystem.DeliveryMan;
import LockerSystem.Package;
import Management.FileManager.PackagesListReader;
import ObserverPattern.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Manager implements Observer {

    private String deliveryManCode;
    private ArrayList<DeliveryMan> deliveryMen = new ArrayList<>();
    private ArrayList<Package> packages = new ArrayList<>();
    private PackagesListReader reader = new PackagesListReader();


    public Manager() throws IOException {
        CodeGenerator generator = new CodeGenerator();
        deliveryManCode =  generator.generateDeliveryManCode();
        reader.addObserver(this);
        addPackages();
    }

    public void createDeliveryMan(String id) {
        DeliveryMan del = new DeliveryMan(id);
        deliveryMen.add(del);
    }

    public void createPackage(String id, double height, double length, double width){
        Package pack = new Package(id, height, length, width);
        packages.add(pack);
    }

    public void addPackages() throws IOException {
        String text = reader.getText();
        StringTokenizer st1 = new StringTokenizer(text, "\n");
        while(st1.hasMoreTokens()) {
            String riga = st1.nextToken();
            StringTokenizer st2 = new StringTokenizer(riga, "   ");
            String id = st2.nextToken();
            if(getPackage(id)==null) {
                createPackage(id, Double.parseDouble(st2.nextToken()), Double.parseDouble(st2.nextToken()), Double.parseDouble(st2.nextToken()));
            }
        }
    }

    public DeliveryMan getDeliveryMan(String id){
        for(DeliveryMan del : deliveryMen){
            if(del.getId().equals(id)){
                return del;
            }
        }
        return null;
    }

    public Package getPackage(String id){
        for(Package pack : packages){
            if(pack.getId().equals(id)){
                return pack;
            }
        }
        return null;
    }

    public void addPackageToDeliveryMan(String delID, String packID) {
        try{
            getDeliveryMan(delID).addPackage(getPackage(packID));
        }  catch(Exception e){
            System.err.println("Invalid ID");
        }
    }

    @Override
    public void update(){
        try {
            packages.clear();
            addPackages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
