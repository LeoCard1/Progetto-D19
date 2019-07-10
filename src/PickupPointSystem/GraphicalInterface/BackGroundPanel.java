package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Toolkit.getDefaultToolkit;

/** This class is the background panel that will contain all
 * the other panels
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
    private String currentPanel = "";


    /**
     * The constructor. Creates boxAccessPanel, viewBoxesPanel, loginDelManPanel and startPanel.
     * @param pipo the PickupPoint
     * @param gra the frame
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
     * This method adds the panels to the panel container panelCont, sets as the first
     * panel startPanel, sets the layout and adds panelCont
     */

    private void initPanel(){
        panelCont.setLayout(cardLayout);
        panelCont.setPreferredSize(new Dimension(width*2/3, height*2/3));
        panelCont.add(boxAccessPanel, "boxAccessPanel");
        panelCont.add(viewBoxesPanel,"viewBoxesPanel");
        panelCont.add(loginDelManPanel,"loginDelManPanel");
        panelCont.add(startPanel, "startPanel");

        changePanel("startPanel");

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/9));
        add(panelCont);
    }

    /**
     * This method sets the background image
     * @param g the Graphics
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/logobackground.jpg");
        img = img.getScaledInstance(getWidth(),getHeight(), Image.SCALE_DEFAULT);
        ImageLoader imgLoader = new ImageLoader();
        imgLoader.loadImage(img, this);
        g.drawImage(img,0,0,this);
        super.paintComponent(g);
    }


    /**
     * This method changes the panel to the one whose name is passed as an argument
     * @param namePanel the panel name
     */

    public void changePanel(String namePanel){

        if (namePanel.equals("startPanel")) {
            startPanel.startTimer();
        }

        if (namePanel.equals("viewBoxesPanel")) {
            viewBoxesPanel.getGridBoxesPanel().checkState();
            viewBoxesPanel.getInfoPackPanel().refresh();
        }

        checkCurrentPanel();
        cardLayout.show(panelCont,namePanel);
        currentPanel = namePanel;
    }


    /**
     * This method checks if you need to call some method of the current panel before
     * changing it
     */

    private void checkCurrentPanel(){
        if(currentPanel.equals("viewBoxesPanel")){
            viewBoxesPanel.getGridBoxesPanel().closeBoxes();
        }
        if(currentPanel.equals("boxAccessPanel")){
            boxAccessPanel.getInsertCodePanel().deleteText();
        }
        if(currentPanel.equals("loginDelManPanel")){
            loginDelManPanel.deleteText();
        }
    }

    /**
     * This method refreshes the boxAccessPanel.
     */

    public void refresh(){
        boxAccessPanel.refresh();
    }

}
