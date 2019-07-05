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
        setHorizontalAlignment(0);
        setFont(new Font("", Font.PLAIN, height/20));
        setVisible(false);
        createTimer();
    }

    /**
     * This method sets the text with the authentication string successfull,
     * makes the label visible and starts the timer
     */

    public void correctCode(){
        setText(correctCodeString);
        setForeground(Color.decode("#228b22"));
        setVisible(true);
        timer.start();
    }

    /**
     * This method sets the text with the authentication string failed, makes
     * the label visible and starts the timer
     */

    public void wrongCode(){
        setText(wrongCodeString);
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
