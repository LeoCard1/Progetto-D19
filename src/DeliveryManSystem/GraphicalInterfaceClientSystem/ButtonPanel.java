package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    ButtonPanel(int width, int height){
        GuiBotton(width,height);
    }

    public void GuiBotton(int width , int height){

        setLayout(new GridLayout(2,2 , width/18,height/18));

        //impostazione bottoni

        JButton impostazioni = new JButton("impostazioni");
        JButton connetti = new JButton("connetti");
        JButton ritiro = new JButton("ritiro");

        //aggiunta bottoni

        add(impostazioni);
        add(connetti);
        add(ritiro);

    }


}
