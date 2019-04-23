package ObserverPattern;

class ConcreteObserver implements Observer


{
    private String idObserver;

    public ConcreteObserver(String idObserver) {
        this.idObserver = idObserver;
    }

    @Override
    public void update()
    {
        System.out.println("Sono " + this.idObserver + ": aggiunto pacco!");
    }

    @Override
    public String toString() {
        return idObserver.toString();
    }
}
