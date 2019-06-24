package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;

public class InfoPackPanel extends JPanel {

    private int width;
    private int height;



    public InfoPackPanel(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        initPanel();
    }

    private void initPanel(){

    }


}
