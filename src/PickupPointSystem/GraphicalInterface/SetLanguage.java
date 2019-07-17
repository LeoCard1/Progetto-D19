package PickupPointSystem.GraphicalInterface;

public class SetLanguage {
    static SetLanguage instance;
    private String language;

    private SetLanguage() {
        this.language = "english";
    }
    // Static method to obtain the instance
    public static synchronized SetLanguage getInstance() {
        if (instance == null)
            instance = new SetLanguage();
        return instance;
    }

    /*
     * setGraIntMain changes the language of the strings in GraIntMain:
     * Index [0]: title
     * Index [1]: first upper tab
     * Index [2]: second upper tab
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
     * setBoxAccessPanel changes the language of the strings in BoxAccessPanel:
     * Index [0]: indice della tendina da scegliere per cambiare lingua (al cambio di lingua si inverte l'ordine della tendina perché si seleziona il valore più in fondo dei due)
     * Index [1]: JLabel di fianco alla tendina
     * Index [2]: primo elemento della tendina (che diventa il secondo al cambio di lingua)
     * Index [3]: secondo elemento della tendina (che diventa il primo al cambio di lingua)
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
     * setLoginDelManPanel changes the language of the strings in LoginDelManPanel:
     * Index [0]: scritta sopra lo spazio in cui inserire il codice
     * Index [1]: scritta di conferma sul bottone appena sotto
     */
    public String[] setLoginDelManPanel(){

        if(language.equals("italiano")){
            String[] s = {"Inserire le Credenziali", "Credenziali Corrette", "Credenziali Errate", "Conferma"};
            return s;
        }else{
            String[] s = {"Insert Your Credentials", "Correct Credentials", "Wrong Credentials", "Confirm"};
            return s;
        }

    }

    /*
     * setInsertCodePanel changes the language of the strings in InsertCodePanel:
     * Index [0]: scritta sopra lo spazio in cui inserire il codice
     * Index [1]: scritta di conferma sul bottone appena sotto
     */
    public String[] setInsertCodePanel(){

        if(language.equals("italiano")){
            String[] s = {"Inserisci Codice", "Conferma", "Codice Corretto", "Codice Errato"};
            return s;
        }else{
            String[] s = {"Insert Code", "Confirm", "Correct Code", "Wrong Code"};
            return s;
        }

    }

    public void changeLanguage(String language){
        this.language=language;
    }
}