package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.ITALIC;

/**
 * This class is the label that will contain warnings for the delivery man
 * @author Andrea Stella
 * @version 1.0.0
 */

public class AlertLabel extends JLabel {

    private Timer timer;
    private String defaultText;
    private boolean hasDefaultText = false;

    /**
     * The constructor. Initializes the label by calling the initLabel
     * method
     */

    public AlertLabel(){
        initLabel();
    }

    /**
     * The constructor. Sets the label text and initializes the label
     * by calling the initLabel method
     * @param text the text to be written in the label
     */

    public AlertLabel(String text){
        super(text);
        initLabel();
    }

    /**
     * This method sets the label icon, the text font and the border
     */

    private void initLabel(){
        Dimension screenSize = getToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("Icons/problem.png")).getImage()
                .getScaledInstance(width*2/55, height/20, Image.SCALE_DEFAULT));
        setIcon(imageIcon);
        Font font = new Font("Arial" ,ITALIC , height/50);
        setBorder(BorderFactory.createTitledBorder(getBorder(),SetDMLanguage.getInstance()
                .setLoginPanel()[7] , ITALIC , 0, font, Color.red));
        createTimer();

    }

    /**
     * This method sets the default text
     * @param defaultText the text that will appear once the timer
     *                    has expired
     */

    public void setDefaultText(String defaultText){
        this.defaultText = defaultText;
        hasDefaultText = true;
    }


    /**
     * This method shows the message passed as an argument for a few
     * seconds
     * @param message the message to show
     */

    public void showMessageForAFewSeconds(String message){
        setText(message);
        setVisible(true);
        timer.start();
    }

    /**
     * This method creates the timer, when the timer expires, the label
     * becomes invisible
     *
     */

    public void createTimer(){
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasDefaultText){
                    setText(defaultText);
                    setVisible(true);
                }  else setVisible(false);
                timer.stop();
            }
        });
    }
}
