package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private Dimension screenSize;
    private int width,height;
    private PannelloIniziale iniziale;
    private PannelloImpostazioni impostazioni;




    public void ClientGUI() {

        //impostazioni frame

        screenSize = getToolkit().getScreenSize();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        width = screenSize.width/4;
        height = screenSize.height/2;
        setSize(new java.awt.Dimension(width,height));
        setResizable(true);
        setTitle("Versione Beta");
        setVisible(true);

        //creazione pannelli

        impostazioni = new PannelloImpostazioni(this);
        iniziale = new PannelloIniziale(this , impostazioni);


        //aggiunta pannelli

        add(impostazioni).setVisible(false);
        add(iniziale);





    }

}

