package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class PannelloIniziale extends JPanel {

    private TextArea testo;
    private String StringaDiConnessione = "Disconnesso";

    PannelloIniziale(Frame f , JPanel pannImpostazioni){

        testo = new TextArea(StringaDiConnessione);
        schermataDiInizio(f , this , pannImpostazioni);

    }

    public void schermataDiInizio(Frame f , JPanel p , JPanel pannImpostazioni){

        setBackground(Color.black);
        //aggiunta area di testo

        add(testo);

        //creazione bottoni

        JButton impostazioni = new JButton("impostazioni");
        JButton connetti = new JButton("connetti");
        JButton ritiro = new JButton("ritiro");

        //aggiunta bottoni

        add(impostazioni);
        add(connetti);
        add(ritiro);

        //azione bottoni

        ButtonAction impostazioniAction = new ButtonAction(f ,p,pannImpostazioni);
        ButtonAction connettiAction = new ButtonAction(f ,p);
        ButtonAction ritiroAction = new ButtonAction(f ,p);
        impostazioni.addActionListener(impostazioniAction);
        connetti.addActionListener(connettiAction);
        ritiro.addActionListener(ritiroAction);

    }

}
