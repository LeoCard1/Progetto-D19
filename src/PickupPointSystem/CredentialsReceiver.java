package PickupPointSystem;



import PickupPointSystem.ObserverPattern.ObserverCredentials;

import java.util.ArrayList;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class CredentialsReceiver {

    private static final CredentialsReceiver instance = new CredentialsReceiver();
    private static final ArrayList<ObserverCredentials> observers = new ArrayList<>();

    private CredentialsReceiver(){

    }

    public static CredentialsReceiver getInstance(){
        return instance;
    }

    public void receiveCredentials(String id, String password){
        notifyObservers(id, password);
    }

    public void addObserver(ObserverCredentials ob){
        observers.add(ob);
    }

    public void notifyObservers(String id, String password){
        for(ObserverCredentials ob : observers){
            ob.updateCredentials(id, password);
        }
    }
}
