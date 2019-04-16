package ObserrverPattern;

/*observer riceve l’aggiornamento di un risultato di un match attraverso il metodo update():
 il quale setta il campo score con l’ultimo score ricevuto e stampa infine a video il nuovo risultato del match.
        Per identificare le stampe a video abbiamo inserito un campo id per identificare i singoli observer*/

public interface Observer {

    public void update(Object o);


}
