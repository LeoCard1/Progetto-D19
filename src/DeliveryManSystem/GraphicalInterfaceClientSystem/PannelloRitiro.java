package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class PannelloRitiro extends JPanel {

    PannelloRitiro(Frame frame ){

        GuiRitiro(frame);
    }



    public void GuiRitiro(Frame frame ){

        setBackground(Color.green);
        JButton indietro = new JButton("indietro");
        add(indietro);
        ButtonAction indietroAction = new ButtonAction(frame , this);
        indietro.addActionListener(indietroAction);




    }
}
