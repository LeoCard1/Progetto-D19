package PickupPointSystem.GraphicalInterface.ErrorGUI;

import PickupPointSystem.GraphicalInterface.AbstractPickupPointFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

public class ErrorGUIMain extends AbstractPickupPointFrame {
    private int width;
    private int height;
    private boolean closeProgram;

    public ErrorGUIMain(String message, boolean closeProgram) throws HeadlessException {
        this.closeProgram = closeProgram;

        Toolkit tk = Toolkit.getDefaultToolkit();
        height = tk.getScreenSize().height/4;
        width = tk.getScreenSize().width/2;

        setTitle("Error");
        setSize(width, height);
        setLocationRelativeTo(null);
        
        initPanel(message);

        setVisible(true);
        if (closeProgram) setDefaultCloseOperation(EXIT_ON_CLOSE);
        else setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void initPanel(String message) {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel messagePanel = new JPanel();
        Image errorImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/wrong.jpg").getScaledInstance(height/4, height/4, Image.SCALE_DEFAULT);
        JLabel messageLabel = new JLabel(" Error: " + message, new ImageIcon(errorImage), JLabel.CENTER);
     
        messageLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, height/9));
        messageLabel.setForeground(new Color(255, 0, 0));

        JPanel okPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font(Font.DIALOG, Font.PLAIN, height/9));

        messagePanel.add(messageLabel);
        okPanel.add(okButton);
        mainPanel.add(messagePanel);
        mainPanel.add(okPanel);
        add(mainPanel);

        ActionListener closeWindowListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                if (closeProgram) dispose();
            }
        };

        okButton.addActionListener(closeWindowListener);
    }
}
