package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAction extends Gui implements ActionListener {
    JFrame f;

    ButtonAction(JFrame f){
        this.f = f;
    }


    public void actionPerformed(ActionEvent event ) {

        f.remove(getPanel());








    }
}
