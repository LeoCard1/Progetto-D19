package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;


public class PannelloImpostazioni extends JPanel {

    PannelloImpostazioni(Frame frame ){
        GuiImpostazioni(frame, this );
    }



    public void GuiImpostazioni(Frame frame , JPanel generico ){

        setBackground(Color.red);
        JButton indietro = new JButton("indietro");
        add(indietro);
        ButtonAction indietroAction = new ButtonAction(frame ,generico);
        indietro.addActionListener(indietroAction);



    }


}
