package DeliveryManSystem.GraphicalInterfaceClientSystem;

public class SetDMLanguage {
    static SetDMLanguage instance;
    private String language;

    private SetDMLanguage() {
        this.language = "english";
    }
    // Metodo statico per ottenere l'istanza
    public static synchronized SetDMLanguage getInstance() {
        if (instance == null)
            instance = new SetDMLanguage();
        return instance;
    }

    public String[] setLoginPanel(){

        if(language.equals("italiano")){
            String[] s = {"1", "Italiano", "Inglese", "Accedi", "Lingua   :", "Password   :", "ID   :",
                          "Messaggio importante!", "Accedi", "<html><center>Password o Id errati</html>",
                          "<html><center>Servizio non disponibile</html>", "<html><center>Inserire ID e Password</html>"};
            return s;
        }else{
            String[] s = {"0", "English", "Italian", "Sign In", "Language   :", "Password   :", "ID   :",
                          "Important Message!", "Sign In", "<html><center>Incorrect Password or Id</html>",
                          "<html><center>Service unavailable</html>", "<html><center>Enter ID and Password</html>"};
            return s;
        }
    }

    public void changeLanguage(String language){
        this.language=language;
    }

}
