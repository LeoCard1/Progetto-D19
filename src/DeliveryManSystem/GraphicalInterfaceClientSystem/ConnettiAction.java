package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryManClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnettiAction implements ActionListener {

    private String password;
    private DeliveryManClient fattorino;
    private String id;
    private Frame frame;
    private JPanel iniziale;
    private JPanel finale;

    ConnettiAction(char[] password , String id , DeliveryManClient fattorino , Frame frame , JPanel iniziale){

        this.password = new String(password);
        this.fattorino = fattorino;
        this.id = id;
        this.frame = frame;
        this.iniziale = iniziale;
        System.out.println(password);
        System.out.println(id);
    }

    public void actionPerformed(ActionEvent e) {


    }
}
