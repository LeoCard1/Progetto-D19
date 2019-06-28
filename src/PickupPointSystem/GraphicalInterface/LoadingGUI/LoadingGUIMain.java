package PickupPointSystem.GraphicalInterface.LoadingGUI;

import javax.swing.*;
import java.awt.*;



/**
 * This class is a loading screen
 * @author Andrea Stella
 * @version 1.0
 */

public class LoadingGUIMain extends JFrame {

    private JLabel labelMessage;

    /**
     * The constructor. Sets some parameters related to the frame
     */

    public LoadingGUIMain(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        int height = tk.getScreenSize().height/4;
        int width = tk.getScreenSize().width/2;
        setTitle("Loading");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        initFrame();
    }

    /**
     * Initialize the frame
     */

    private void initFrame(){
        JPanel mainPanel = new JPanel();
        JPanel panelMessage = new JPanel();
        labelMessage = new JLabel();
        labelMessage.setText("<html></html>");
        labelMessage.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelMessage.setHorizontalAlignment(0);
        panelMessage.setLayout(new BorderLayout());
        panelMessage.add(labelMessage, BorderLayout.CENTER);
        ImageIcon loadingImg = new ImageIcon("src/PickupPointSystem/GraphicalInterface/Icons/loading.gif");
        JLabel progress = new JLabel(loadingImg);
        mainPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(progress);
        mainPanel.add(panelMessage);
        add(mainPanel);
    }

    /**
     * Sets the label text
     * @param message the string to add to the label
     */

    public void setText(String message){
        StringBuilder text = new StringBuilder();
        text.append("<html><font face = 'Times New Roman' size = 5 color = '#696969'>");
        text.append(message);
        text.append("</html>");
        labelMessage.setText(text.toString());
    }

    /**
     * Closes the frame
     */

    public void closeFrame(){
        setVisible(false);
    }

}
