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
    private PickupPointInfoPanel pickupPointInfoPanel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel panelContainer = new JPanel();

    /**
     * The constructor
     * @param width the panel width
     * @param height the panel height
     */

    public BackgroundPanel(int width, int height) {
        this.height = height;
        setBackground(Color.decode("#232F3E"));

        panelContainer.setPreferredSize(new Dimension(width*15/16, height*15/16));
        panelContainer.setOpaque(false);

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
     * @param panelName the panel name
     */

    public void changePanel(String panelName) {
        checkPanelActions(panelName);
        cardLayout.show(panelContainer, panelName);
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
        if(panelName.equals("pickupPointInfoPanel")){
            pickupPointInfoPanel.refreshPickupPointList();
        }

    }

    /**
     * This method create the startingPanel, the packagePanel and the
     * pickupPointInfoPanel and adds they to the panelContainer
     * @param deliveryMan the delivery man created after login
     */

    public void update(DeliveryMan deliveryMan) {
        startingPanel = new StartingPanel(this, deliveryMan, height);
        packagePanel = new PackagePanel(this, deliveryMan, height);
        pickupPointInfoPanel = new PickupPointInfoPanel(this, deliveryMan, height);
        panelContainer.add(startingPanel,"startingPanel");
        panelContainer.add(packagePanel,"packagePanel");
        panelContainer.add(pickupPointInfoPanel, "pickupPointInfoPanel");
    }


}
