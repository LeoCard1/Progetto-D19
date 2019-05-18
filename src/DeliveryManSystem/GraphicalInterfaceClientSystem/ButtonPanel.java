package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JFrame f;

    ButtonPanel(int width, int height , JFrame f){

        this.f = f;
        GuiBotton(width,height,f);
    }

    public void GuiBotton(int width , int height,JFrame f){

        setLayout(new GridLayout(2,2 , width/18,height/18));

        //impostazione bottoni

        JButton impostazioni = new JButton("impostazioni");
        JButton connetti = new JButton("connetti");
        JButton ritiro = new JButton("ritiro");

        //aggiunta bottoni

        add(impostazioni);
        add(connetti);
        add(ritiro);

        //azione bottoni

        ButtonAction impostazioniAction = new ButtonAction(f);
        ButtonAction connettiAction = new ButtonAction(f);
        ButtonAction ritiroAction = new ButtonAction(f);
        impostazioni.addActionListener(impostazioniAction);
        connetti.addActionListener(connettiAction);
        ritiro.addActionListener(ritiroAction);


        //impostazioni.addActionListener();

    }


}
