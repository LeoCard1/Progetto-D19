package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class PannelloIniziale extends JPanel {

    private TextArea testo;
    private String StringaDiConnessione = "Disconnesso";

    PannelloIniziale(Frame f ){

        testo = new TextArea(StringaDiConnessione);
        schermataDiInizio(f);

    }

    public void schermataDiInizio(Frame f ){

        //setBackground(Color.black);
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridLayout(4 , 0));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,5));

        JPanel prova = new JPanel();
        prova.setBorder(BorderFactory.createTitledBorder(("STATO CONNESSIONE" + " : " + StringaDiConnessione)));
        prova.setBackground(Color.red);


        add(prova);

        add(prova);
       // JPanel prova1 = new JPanel();
       // add(prova1);
        //aggiunta area di testo

        //add(testo);

        //creazione bottoni

        JButton impostazioni = new JButton("impostazioni");
        JButton connetti = new JButton("connetti");
        JButton ritiro = new JButton("ritiro");

        //impostazione bottoni

        impostazioni.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        connetti.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        ritiro.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));



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
