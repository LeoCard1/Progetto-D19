package ObserrverPattern;

import java.util.ArrayList;
import java.util.List;

public class ObservableMatch {

    private String matchScore;
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer channel) {
        //notifico il risultato iniziale non appena un observer si sottoscrive
        channel.update(this.matchScore);
        this.observers.add(channel);

       /* Metodo da invocare per registrare un observer. All’interno di tale metodo invochiamo  il metodo update
        per far ricevere all observer il risultato attuale del match
        altrimenti quest’ultimo avrebbe dovuto attendere il primo aggiornamento del risultato per ricevere il risultato del match*/
    }
    public void removeObserver(Observer channel) {
        this.observers.remove(channel);
    }
    public ObservableMatch() {
        this.matchScore = "0-0";
    }
    public void setMatchScore(String newScore) {
        this.matchScore = newScore;
        for (Observer observer : this.observers) {
            observer.update(this.matchScore);
        }
    }
}
