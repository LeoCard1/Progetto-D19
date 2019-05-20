package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
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

        //impostazioni pannello

        setLayout(new GridLayout(4 ,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,0));

        JPanel statoDiConnessione = new JPanel();
        statoDiConnessione.setBorder(BorderFactory.createTitledBorder(("STATO CONNESSIONE" + " : " + StringaDiConnessione)));
        statoDiConnessione.setBackground(Color.red);
        add(statoDiConnessione);

        //creazione bottoni

        JButton impostazioni = new JButton("impostazioni" );
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
