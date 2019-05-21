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

    public String setTitle(){

        if(language.equals("italiano")){
            return "Sistema Punto Di Ritiro";
        }else{
            return "Pickup Point System";
        }
    }

    public void changeLanguage(String language){
        this.language=language;
    }
}