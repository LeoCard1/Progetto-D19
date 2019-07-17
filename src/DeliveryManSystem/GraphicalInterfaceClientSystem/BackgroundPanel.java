package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;
import DeliveryManSystem.Observers.ObserverLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroundPanel extends JPanel implements ObserverLogin {

    private int width;
    private int height;
    private LoginPanel loginPanel;
    private StartingPanel startingPanel;
    private PackagePanel packagePanel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel panelContainer = new JPanel();
    private String currentPanel = "";

    public BackgroundPanel(int width, int height) {
        this.width = width;
        this.height = height;

        panelContainer.setPreferredSize(new Dimension(width*15/16, height*15/16));

        loginPanel = new LoginPanel(this, width, height );
        loginPanel.addObserver(this);

        initPanel();
    }

    private void initPanel() {
        panelContainer.setLayout(cardLayout);
        panelContainer.add(loginPanel, "loginPanel");


        changePanel("loginPanel");

        add(panelContainer);
    }

    public void changePanel(String panelName) {
        checkCurrentPanel();
        cardLayout.show(panelContainer, panelName);
        currentPanel = panelName;
        if (panelName.equals("PackagePanel")){
            packagePanel.RefreshPackageList();
        }
    }

    public void checkCurrentPanel(){
        if(currentPanel.equals("loginPanel")){
            loginPanel.deleteText();
        }
    }

    @Override
    public void update(DeliveryMan deliveryMan) {
        startingPanel = new StartingPanel(this, deliveryMan, width, height);
        packagePanel = new PackagePanel(this, deliveryMan, width, height);
        panelContainer.add(startingPanel,"startingPanel");
        panelContainer.add(packagePanel,"packagePanel");
    }


}
