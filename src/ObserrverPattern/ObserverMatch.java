package ObserrverPattern;

//implementa l’interfaccia dell’Observer definendo il comportamento in caso di cambio di stato del soggetto osservato
//concrete observer

public class ObserverMatch implements Observer{

    private String id;
    private String score;

    public ObserverMatch(String id) {
        this.id = id;
    }

    @Override
    public void update(Object score) {
        System.out.println("(observer-"+id+")risultato: "+ (String) score);
        this.score = (String) score;
    }



}
