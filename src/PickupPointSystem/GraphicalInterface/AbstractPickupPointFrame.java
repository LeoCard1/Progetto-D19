package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Andrea Stella
 * @version 1.0.0
 */

public abstract class AbstractPickupPointFrame extends JFrame {

    /**
     * The constructor. Sets the logo and the theme
     */

    public AbstractPickupPointFrame(){
        Image logoImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/logomail.png");
        setIconImage(logoImage);
        setTheme("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    }

    /**
     * This method changes the frame theme.
     * @param className the name of the theme
     */

    public void setTheme(String className){
        try {
            UIManager.setLookAndFeel(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
