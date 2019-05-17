package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private JFrame j;
    private Dimension screenSize;
    private JPanel pannelloBottoni;
    private int width,height;




    public void ClientGUI() {
        j = new JFrame();
        screenSize = getToolkit().getScreenSize();
        j.setLayout(new GridLayout());
        j.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        width = screenSize.width/4;
        height = screenSize.height/2;
        j.setSize(new java.awt.Dimension(width,height));
        j.setResizable(true);
        j.setTitle("Versione Beta");
        j.setVisible(true);

        //impostazioni pannello bottoni

        pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(new GridLayout(2,2 , width/18,height/18));


        //impostazione bottoni

        JButton impostazioni = new JButton("impostazioni");
        JButton connetti = new JButton("connetti");
        JButton ritiro = new JButton("ritiro");


        //aggiunta bottoni

        pannelloBottoni.add(impostazioni);
        pannelloBottoni.add(connetti);
        pannelloBottoni.add(ritiro);

        j.add(pannelloBottoni);


    }
}

