package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAction  implements ActionListener {

    private Frame frame;
    private JPanel iniziale;
    private JPanel finale;

    ButtonAction(Frame frame, JPanel iniziale){

        this.frame = frame;
        this.iniziale = iniziale;

    }

    public void actionPerformed(ActionEvent event ) {

        switch (event.getActionCommand()){
            /*case "impostazioni" :
                frame.remove(iniziale);
                finale = new PannelloImpostazioni(frame);
                frame.add(finale);
                frame.repaint();
                frame.validate();
                break;*/

            /*case "connetti" :
                frame.remove(iniziale);
                finale = new LoginPanel(frame);
                frame.add(finale);
                frame.repaint();
                frame.validate();
                break;*/

            case "ritiro" :
                frame.remove(iniziale);
                finale = new PannelloRitiro(frame);
                frame.add(finale);
                frame.repaint();
                frame.validate();
                break;
            case "indietro" :
                frame.remove(iniziale);
                finale = new PannelloIniziale(frame);
                frame.add(finale);
                frame.repaint();
                frame.validate();

                break;

        }
    }

}
