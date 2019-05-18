package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class PannelloIniziale extends JPanel {

    private TextArea testo;
    private String StringaDiConnessione = "Disconnesso";

    PannelloIniziale(Frame f ){

        testo = new TextArea(StringaDiConnessione);
        schermataDiInizio(f);

    }

    public void schermataDiInizio(Frame f ){

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

        ButtonAction impostazioniAction = new ButtonAction(f , this);
        ButtonAction connettiAction = new ButtonAction(f , this);
        ButtonAction ritiroAction = new ButtonAction(f , this);
        impostazioni.addActionListener(impostazioniAction);
        connetti.addActionListener(connettiAction);
        ritiro.addActionListener(ritiroAction);

    }

}
