package PickupPointSystem.GraphicalInterface;

public class SetLanguage {
    static SetLanguage instance;
    private String language;

    private SetLanguage() {
        this.language = "english";
    }
    // Metodo statico per ottenere l'istanza
    public static synchronized SetLanguage getInstance() {
        if (instance == null)
            instance = new SetLanguage();
        return instance;
    }

    /*
     * setGraIntMain cambia la lingua delle string in GraIntMain:
     * Indice [0]: titolo
     * Indice [1]: prima tab in alto
     * Indice [2]: seconda tab in alto
     */
    public String[] setGraIntMain(){

        if(language.equals("italiano")){
            String[] s = {"Sistema Punto di Ritiro", "Sistema Box", "Visualizza Box"};
            return s;
        }else{
            String[] s = {"Pickup Point System", "Locker System", "View Boxes"};
            return s;
        }
    }

    /*
     * setBoxAccessPanel cambia la lingua delle string in BoxAccessPanel:
     * Indice [0]: indice della tendina da scegliere per cambiare lingua (al cambio di lingua si inverte l'ordine della tendina perché si seleziona il valore più in fondo dei due)
     * Indice [1]: JLabel di fianco alla tendina
     * Indice [2]: primo elemento della tendina (che diventa il secondo al cambio di lingua)
     * Indice [3]: secondo elemento della tendina (che diventa il primo al cambio di lingua)
     */
    public String[] setBoxAccessPanel(){

        if(language.equals("italiano")){
            String[] s = {"1", "Lingua:", "Italiano", "Inglese"};
            return s;
        }else{
            String[] s = {"0", "Language:", "English", "Italian"};
            return s;
        }

    }


    /*
     * setInsertCodePanel cambia la lingua delle string in InsertCodePanel:
     * Indice [0]: scritta sopra lo spazio in cui inserire il codice
     * Indice [1]: scritta di conferma sul bottone appena sotto
     */
    public String[] setInsertCodePanel(){

        if(language.equals("italiano")){
            String[] s = {"Inserisci Codice", "Conferma"};
            return s;
        }else{
            String[] s = {"Insert Code", "Confirm"};
            return s;
        }

    }

    public void changeLanguage(String language){
        this.language=language;
    }
}