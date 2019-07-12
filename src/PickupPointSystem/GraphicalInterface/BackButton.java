package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * This class represents the back button
 * @author Andrea Stella
 * @version 1.0
 */

public class BackButton extends JButton {

    /**
     * The constructor. Sets the icon and sets false the content area filled
     */

    public BackButton(){
        Toolkit tk = getDefaultToolkit();
        int height = tk.getScreenSize().height;
        Image backImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/back.jpg").getScaledInstance(height/20, height/20,Image.SCALE_DEFAULT);
        super.setIcon(new ImageIcon(backImage));
        super.setContentAreaFilled(false);
    }
}
