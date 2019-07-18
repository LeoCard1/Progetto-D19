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

        ClientGUI();
    }

    /**
     * This method creates the frame and add to it the Login Panel
     */
    public void ClientGUI() {
        setTitle("Amazon");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        screenSize = getToolkit().getScreenSize();
        width = screenSize.width*2/11;
        height = screenSize.height/2;
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icons/amazonFakeIcon.png"));
        setIconImage(image);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.black,height/80 ));

        add(new BackgroundPanel(width, height));
        setVisible(true);
    }




}

