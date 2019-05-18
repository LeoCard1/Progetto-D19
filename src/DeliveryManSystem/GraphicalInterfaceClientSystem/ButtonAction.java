package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAction  implements ActionListener {
    Frame f;
    JPanel iniziale;
    JPanel generico;

    ButtonAction(Frame f , JPanel iniziale){
        this.f = f;
        this.iniziale = iniziale;
    }

    ButtonAction(Frame f , JPanel iniziale , JPanel generico){

        this.f = f;
        this.iniziale = iniziale;
        this.generico = generico;

    }


    public void actionPerformed(ActionEvent event ) {

        switch (event.getActionCommand()){
            case "impostazioni" :
                generico.setVisible(true);
                f.repaint();
                f.validate();

            case "indietro" :
                generico.setVisible(false);
                f.repaint();
                f.validate();
                iniziale.setVisible(true);
        }





    }
}
