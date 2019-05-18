package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private Dimension screenSize;
    private int width,height;
    private String StringaDiConnessione = "Disconnesso";
    private Container frame;
    private ButtonPanel panel;




    public void ClientGUI() {

        frame = Frame();
        panel = new ButtonPanel(width,height,this);
        frame.add(testo());
        frame.add(panel);


    }

    //impostazioni frame

    public Container Frame(){

        frame = getContentPane();
        screenSize = getToolkit().getScreenSize();
        setLayout(new GridLayout(2,0));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        width = screenSize.width/4;
        height = screenSize.height/2;
        setSize(new java.awt.Dimension(width,height));
        setResizable(true);
        setTitle("Versione Beta");
        setVisible(true);
        return frame;

    }

    //area di testo

    public TextArea testo(){

        TextArea testo = new TextArea(StringaDiConnessione);
       return testo;

    }

    public void reloadClienGui(Container f){

        panel.setVisible(false);

    }

    public Container getFrame() {
        return frame;
    }

    public ButtonPanel getPanel() {
        return panel;
    }
}

