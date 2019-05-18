package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class PannelloConnetti extends JPanel {

    PannelloConnetti(Frame frame ){

        GuiConnetti(frame);
    }



    public void GuiConnetti(Frame frame ){

        setBackground(Color.blue);
        JButton indietro = new JButton("indietro");
        add(indietro);
        ButtonAction indietroAction = new ButtonAction(frame , this);
        indietro.addActionListener(indietroAction);




    }
}
