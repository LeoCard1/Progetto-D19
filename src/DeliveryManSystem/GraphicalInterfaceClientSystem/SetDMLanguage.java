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
            String[] s = {"1", "Italiano", "Inglese", "Login", "Lingua   :", "Password   :", "Id   :",
                          "Messaggio importante!", "Login", "Password o Id errati", "Servizio non disponibile"};
            return s;
        }else{
            String[] s = {"0", "English", "Italian", "Sign In", "Language   :", "Password   :", "Id   :",
                          "Important Message!", "Sign In", "Incorrect Password or Id", "Service unavailable"};
            return s;
        }
    }

    public void changeLanguage(String language){
        this.language=language;
    }

}
