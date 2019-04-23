package ObserverPattern;

import LockerSystem.Package;

public class Prova {
    public static void main(String[] args) {
        Box box = new LargeBox();
        Observer observer = new ConcreteObserver("Corriere");

        box.addObserver(observer);  //aggiungo 1 observer nella lista degli observers

        box.addPackage(new Package("fds",3,3,3));
        box.addPackage(new Package("fds",3,3,3));

        //se rimuovi l observer non notifica piu  aggiunta dei prossimi pacchi che aggiungeremo
       //box.removeObserver(observer);

        box.addPackage(new Package("fds",3,3,3));
        box.addPackage(new Package("fds",3,3,3));

    }
}
