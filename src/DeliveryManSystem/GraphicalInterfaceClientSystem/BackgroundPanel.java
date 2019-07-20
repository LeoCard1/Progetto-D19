package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;
import DeliveryManSystem.Observers.ObserverLogin;

import javax.swing.*;
import java.awt.*;

/**
 * This class initializes the backgroundPanel
 * @author Gruppo D19
 */

public class BackgroundPanel extends JPanel implements ObserverLogin {

    private int height;
    private LoginPanel loginPanel;
    private StartingPanel startingPanel;
    private PackagePanel packagePanel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel panelContainer = new JPanel();
    private String currentPanel = "";

    /**
     * The constructor
     * @param width
     * @param height
     */

    public BackgroundPanel(int width, int height) {
        this.height = height;

        panelContainer.setPreferredSize(new Dimension(width*15/16, height*15/16));

        loginPanel = new LoginPanel(this, width, height );
        loginPanel.addObserver(this);

        initPanel();
    }

    /**
     * This method initializes the panelContainer
     * and add to it the loginPanel
     */

    private void initPanel() {
        panelContainer.setLayout(cardLayout);
        panelContainer.add(loginPanel, "loginPanel");

        changePanel("loginPanel");

        add(panelContainer);
    }

    /**
     * This method change the current panel with the panel that have the name equals to the panelName
     * @param panelName
     */

    public void changePanel(String panelName) {
        checkPanelActions(panelName);
        cardLayout.show(panelContainer, panelName);
        currentPanel = panelName;
    }

    /**
     * This method runs the panel to change methods
     */

    public void checkPanelActions(String panelName){
        if(panelName.equals("loginPanel")){
            loginPanel.deleteText();
        }
        if(panelName.equals("startingPanel")){
            startingPanel.refreshPickupPointsList();
        }
        if(panelName.equals("packagePanel")){
            packagePanel.refreshPackageList();

        }

    }

    /**
     * This method create the startingPanel and the packagePanel
     * and add they to the panelContainer
     * @param deliveryMan
     */

    public void update(DeliveryMan deliveryMan) {
        startingPanel = new StartingPanel(this, deliveryMan, height);
        packagePanel = new PackagePanel(this, deliveryMan, height);
        panelContainer.add(startingPanel,"startingPanel");
        panelContainer.add(packagePanel,"packagePanel");
    }


}
