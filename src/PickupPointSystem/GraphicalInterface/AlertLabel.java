package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * This class is the label that contains the successfull and failed
 * authentication strings
 * @author Andrea Stella
 * @version 1.0
 */

public class AlertLabel extends JLabel {

    private String correctCodeString;
    private String wrongCodeString;
    private Timer timer;
    private Image correctImage;
    private Image wrongImage;

    /**
     * The constructor. Creates the AlertLabel receiving as argument the string
     * to be shown in case of successfull authentication and the one in case of
     * authentication failed
     * @param correctCodeString the correct code string
     * @param wrongCodeString the wrong code string
     */

    public AlertLabel(String correctCodeString, String wrongCodeString){

        this.correctCodeString = correctCodeString;
        this.wrongCodeString = wrongCodeString;
        Toolkit tk = getDefaultToolkit();
        int height = tk.getScreenSize().height;
        correctImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/correct.jpg").getScaledInstance(height/20, height/20,Image.SCALE_DEFAULT);
        wrongImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/wrong.jpg").getScaledInstance(height/20, height/20,Image.SCALE_DEFAULT);
        setHorizontalAlignment(0);
        setFont(new Font("", Font.PLAIN, height/25));
        setVisible(false);
        createTimer();
    }

    /**
     * This method sets the text with the authentication string successfull,
     * makes the label visible and starts the timer
     */

    public void correctCode(){
        setIcon(new ImageIcon(correctImage));
        setText(" "+correctCodeString);
        setForeground(Color.decode("#228b22"));
        setVisible(true);
        timer.start();
    }

    /**
     * This method sets the text with the authentication string failed, makes
     * the label visible and starts the timer
     */

    public void wrongCode(){
        setIcon(new ImageIcon(wrongImage));
        setText(" "+wrongCodeString);
        setForeground(Color.RED);
        setVisible(true);
        timer.start();
    }

    /**
     * This method creates the timer, when the timer expires, the label
     * becomes invisible
     *
     */

    public void createTimer(){
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                timer.stop();
            }
        });
    }
}
