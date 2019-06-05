package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class ButtonBox extends JButton {

    private Image packImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/pack.jpg");
    private Image emptyBoxImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/emptybox.jpg");

    /**
     * The constructor.
     * @param boxCode
     */

    public ButtonBox(String boxCode){
        super(boxCode);
    }

    /**
     * This method sets the packImage Image as the button icon if the foreground is red,
     * otherwise sets the emptyBoxImage Image as icon.
     */

    public void setIcon(){
        Image img;
        if(getForeground()==Color.RED){
            img = packImage;
        }  else {
            img = emptyBoxImage ;
        }
        img = img.getScaledInstance(getWidth()+8, getHeight()-3,Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(img));
    }

    /**
     * This method removes the icon from the button.
     */

    public void removeIcon(){
        setIcon(null);
    }

}
