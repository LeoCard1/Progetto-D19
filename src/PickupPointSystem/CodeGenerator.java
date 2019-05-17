package PickupPointSystem;

import java.util.Random;

public class CodeGenerator {

    /*
     *  -generateBoxPassword: genera password per sbloccare box in base al toString della box.
     *  -generateDeliveryManCode: genera password per DeliveryMan.
     */


    public String generateBoxPassword(String boxToString) {
        String[] division = boxToString.split("\t");
        division[2] = division[2].replaceAll("\\D","");

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String password = new String();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int n = rand.nextInt(25);
            char c = letters.charAt(n);
            password = password + c;
        }
        password = password + division[0] + division[2];
        return password.replaceAll("\\s+","");
    }

    public String generateDeliveryManCode() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password = new String();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(35);
            char c = letters.charAt(n);
            password = password + c;
        }
        return password;
    }

}
