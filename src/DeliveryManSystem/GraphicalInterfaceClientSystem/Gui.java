package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private Dimension screenSize;
    private int width,height;

    public Gui(){
        ClientGUI();
    }

    public void ClientGUI() {

        //impostazioni frame

        screenSize = getToolkit().getScreenSize();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        width = screenSize.width/6;
        height = screenSize.height/2;
        setSize(new java.awt.Dimension(width,height));
        setResizable(false);
        setTitle("Versione Beta");
        setVisible(true);

        //creazione e aggiunta pannello iniziale

        add(new PannelloIniziale(this));



    }


}

