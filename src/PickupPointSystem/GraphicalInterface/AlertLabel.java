package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertLabel extends JLabel {

    private String correctCodeString;
    private String wrongCodeString;
    private Timer timer;

    public AlertLabel(String correctCodeString, String wrongCodeString){
        this.correctCodeString = correctCodeString;
        this.wrongCodeString = wrongCodeString;
        setHorizontalAlignment(0);
        setFont(new Font("", Font.PLAIN, 24));
        setVisible(false);
        createTimer();
    }

    public void correctCode(){
        setText(correctCodeString);
        setForeground(Color.decode("#228b22"));
        setVisible(true);
        timer.start();
    }

    public void wrongCode(){
        setText(wrongCodeString);
        setForeground(Color.RED);
        setVisible(true);
        timer.start();
    }

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
