package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.ObserverPattern.Observer;
import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class BackGroundPanel extends JPanel {

    private int height;
    private int width;
    private BoxAccessPanel boxAccessPanel;
    private ViewBoxesPanel viewBoxesPanel;
    private StartPanel startPanel;
    private LoginDelManPanel loginDelManPanel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel panelCont = new JPanel();

    /**
     * The constructor. Creates boxAccessPanel, viewBoxesPanel and startPanel.
     * @param pipo
     * @param gra
     */

    public BackGroundPanel(PickupPoint pipo, GraIntMain gra){
        boxAccessPanel = new BoxAccessPanel(pipo, gra, this);
        viewBoxesPanel = new ViewBoxesPanel(pipo,this);
        loginDelManPanel = new LoginDelManPanel(pipo,this);
        startPanel = new StartPanel(this);
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;
        initPanel();
    }

    /**
     * This method adds the panels to panelCont and sets as the first panel startPanel.
     */

    private void initPanel(){
        panelCont.setLayout(cardLayout);
        panelCont.add(boxAccessPanel, "boxAccessPanel");
        panelCont.add(viewBoxesPanel,"viewBoxesPanel");
        panelCont.add(loginDelManPanel,"loginDelManPanel");
        panelCont.add(startPanel, "startPanel");
        cardLayout.show(panelCont, "startPanel");

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/10));
        add(panelCont);
    }

    /**
     * This method sets the panel background.
     * @param g
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/logobackground.jpg");
        img = img.getScaledInstance(width*2/3,height*2/3,Image.SCALE_DEFAULT);
        loadImage(img);
        g.drawImage(img,0,0,this);
        super.paintComponent(g);
    }

    /**
     * This method ensures that the image is loaded into memory before being used,
     * blocks the execution of the code until the image is actually loaded into
     * memory.
     * @param img
     */
    private void loadImage(Image img){
        MediaTracker track = new MediaTracker(this);
        track.addImage(img,0);
        try {
            track.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method canges the panel to the one whose name is passed as an argument.
     * @param namePanel
     */

    public void changePanel(String namePanel){
        cardLayout.show(panelCont,namePanel);
    }

    /**
     * This method refreshes the boxAccessPanel.
     */

    public void refresh(){
        boxAccessPanel.refresh();
    }
}
