package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

/**
 * This class create the DeliveryManGui's Frame
 * @author Roberto Zappa
 * @version 1
 */

public class Gui extends JFrame {

    private Dimension screenSize;
    private int width, height;

    public Gui() {

        initFrame();
    }

    /**
     * This method creates the frame and adds into it the BackgroundPanel
     */

    public void initFrame() {

        setTheme("javax.swing.plaf.metal.MetalLookAndFeel");
        setTitle("Smart Locker");
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icons/logo.jpg"));
        setIconImage(image);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        screenSize = getToolkit().getScreenSize();
        width = screenSize.width*2/11;
        height = screenSize.height/2;
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);
        setResizable(false);

        getRootPane().setBorder(BorderFactory.createLineBorder(Color.black,height/80 ));

        add(new BackgroundPanel(width, height));
        setVisible(true);
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

