package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;


public class PannelloImpostazioni extends JPanel {

    PannelloImpostazioni(Frame frame ){

        GuiImpostazioni(frame);
    }



    public void GuiImpostazioni(Frame frame ){

        setBackground(Color.red);
        JButton indietro = new JButton("indietro");
        add(indietro);
        ButtonAction indietroAction = new ButtonAction(frame , this);
        indietro.addActionListener(indietroAction);




    }


}
