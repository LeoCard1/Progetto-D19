package GraphicalInterface.ErrorGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorGUIMain extends JFrame {
    private int width;
    private int height;

    public ErrorGUIMain(String message) throws HeadlessException {
        Toolkit tk = Toolkit.getDefaultToolkit();
        height = tk.getScreenSize().height/4;
        width = tk.getScreenSize().width/2;

        setTitle("Error");
        setSize(width, height);
        setLocation(width/2, 3*height/2);

        initPanel(message);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initPanel(String message) {
        JPanel mainPanel = new JPanel();
        // mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, height/4));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel messagePanel = new JPanel();
        JLabel messageLabel = new JLabel("Error: " + message);
        messageLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        messageLabel.setForeground(new Color(255, 0, 0));

        JPanel okPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));

        messagePanel.add(messageLabel);
        okPanel.add(okButton);
        mainPanel.add(messagePanel);
        mainPanel.add(okPanel);
        add(mainPanel);

        ActionListener closeWindowListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        };

        okButton.addActionListener(closeWindowListener);
    }
}
