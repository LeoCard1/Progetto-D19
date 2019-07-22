package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.ITALIC;

/**
 * This class is the label that will contain warning and success messages
 * for the delivery man
 * @author Andrea Stella
 * @version 1.0.0
 */

public class AlertLabel extends JLabel {

    private Timer timer;
    private String defaultText;
    private boolean hasDefaultText = false;
    private int height;
    private boolean isDefaultWarning;

    /**
     * The constructor. Initializes the label by calling the initLabel
     * method
     */

    public AlertLabel(){
        initSize();
        initLabel();
    }

    /**
     * The constructor. Sets the label text and initializes the label
     * by calling the initLabel method
     * @param text the text to be written in the label
     */

    public AlertLabel(String text, boolean isWarning){
        super(text);
        initSize();
        if(isWarning){
            setWarningIcon();
        } else setSuccessIcon();
        initLabel();
    }

    /**
     * This method sets the text font, the border and creates the timer
     */

    private void initLabel(){
        Font font = new Font("Arial" ,ITALIC , height/50);
        setBorder(BorderFactory.createTitledBorder(getBorder(),SetDMLanguage.getInstance()
                .setLoginPanel()[7] , ITALIC , 0, font, Color.red));
        createTimer();
    }

    /**
     * This method sets the success icon
     */

    private void setSuccessIcon(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("Icons/correct.jpg")).getImage()
                .getScaledInstance(height/20, height/20, Image.SCALE_DEFAULT));
        setIcon(imageIcon);
    }

    /**
     * This method sets the warning icon
     */

    private void setWarningIcon(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("Icons/problem.png")).getImage()
                .getScaledInstance(height/20, height/20, Image.SCALE_DEFAULT));
        setIcon(imageIcon);
    }

    /**
     * This method sets the label text and the label icon
     * @param text the label text
     * @param isWarning specify if the icon is a warning or a success
     */

    public void setTextAndIcon(String text, boolean isWarning){
        setText(text);
        if(isWarning){
            setWarningIcon();
        } else setSuccessIcon();
    }

    /**
     * This method sets the default text and icon
     * @param defaultText the default text
     * @param isWarning specify if the icon is a warning or a success
     */

    public void setDefaultTextAndIcon(String defaultText, boolean isWarning){
        this.defaultText = defaultText;
        this.isDefaultWarning = isWarning;
        hasDefaultText = true;
    }

    /**
     * This method shows the message passed as an argument for a few
     * seconds
     * @param message the message to show
     * @param isWarning specify is the icon is a warning or a success
     */

    public void showMessageForAFewSeconds(String message, boolean isWarning){
        setText(message);
        if(isWarning){
            setWarningIcon();
        } else setSuccessIcon();
        setVisible(true);
        timer.start();
    }

    /**
     * This method creates the timer, when the timer expires, the label
     * becomes invisible if it has not the default text, else shows the
     * default text and the default icon
     */

    public void createTimer(){
        timer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasDefaultText){
                    setText(defaultText);
                    if(isDefaultWarning){
                        setWarningIcon();
                    }else setSuccessIcon();
                    setVisible(true);
                }  else setVisible(false);
                timer.stop();
            }
        });
    }

    /**
     * This method initializes the height
     */

    private void initSize(){
        Dimension screenSize = getToolkit().getScreenSize();
        height = screenSize.height;
    }
}
