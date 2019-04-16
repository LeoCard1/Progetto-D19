package ObserrverPattern;

public class Main {

    public static void main(String [] args) {

        ObservableMatch match = new ObservableMatch();
        Observer observer1 = new ObserverMatch("Cliente");
        Observer observer2 = new ObserverMatch("Corriere");

        //Viene creato l’observable di un match. Lo score del match è inizializzato nel costruttore a “0-0”.
        match.addObserver(observer1);
        match.addObserver(observer2); //aggiungo due osservatori Cliente e Corriere

        match.setMatchScore("1-0");
        match.removeObserver(observer2); //rimuovo Cliente
        match.setMatchScore("2-0");

    }
}
