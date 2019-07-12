package PickupPointSystem;



import PickupPointSystem.ObserverPattern.ObserverCredentials;

import java.util.ArrayList;

/**
 * This Singleton class is used to forward credentials
 * @author Andrea Stella
 * @version 1.0
 */

public class CredentialsReceiver {

    private static final CredentialsReceiver instance = new CredentialsReceiver();
    private static final ArrayList<ObserverCredentials> observers = new ArrayList<>();

    private CredentialsReceiver(){

    }

    /**
     * @return the one and only instance of the class
     */

    public static synchronized CredentialsReceiver getInstance(){
        return instance;
    }

    /**
     * This method receives the credentials passed as an argument and forwards
     * them to the observers who need it
     * @param id the id
     * @param password the password
     */

    public void receiveCredentials(String id, String password){
        notifyObservers(id, password);
    }

    /**
     * This method adds an ObserverCredentials to the observers ArrayList
     * @param ob the ObserverCredentials to add
     */

    public void addObserver(ObserverCredentials ob){
        observers.add(ob);
    }

    /**
     * This method notifies the observers by passing the id and password
     * passed as an argument
     * @param id the id
     * @param password the password
     */

    private void notifyObservers(String id, String password){
        for(ObserverCredentials ob : observers){
            ob.updateCredentials(id, password);
        }
    }
}
