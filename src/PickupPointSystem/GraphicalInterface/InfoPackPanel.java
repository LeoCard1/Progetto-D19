package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;

import static java.awt.Toolkit.getDefaultToolkit;

public class InfoPackPanel extends JPanel {

    private int width;
    private int height;



    public InfoPackPanel(){
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;
        setPreferredSize(new Dimension(width*2/6, height*2/3));
        initPanel();
    }

    private void initPanel(){

    }


}
