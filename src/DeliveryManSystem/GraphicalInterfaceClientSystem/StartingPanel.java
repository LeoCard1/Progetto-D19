package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Font.ITALIC;

/**
 * This Class creates the main panel used in the PDA
 * @author Gruppo D19
 * @version 1.0.2
 */

public class StartingPanel extends JPanel implements ActionListener {

    private DeliveryMan deliveryMan;
    private JButton viewPackage;
    private JButton logOut;
    private JLabel instructionLabel;
    private JComboBox pickupPointIdSelector;
    private BackgroundPanel bgp;
    private int width;
    private int height;
    private JPanel subPanel;

    StartingPanel( BackgroundPanel bgp, DeliveryMan deliveryman, int width, int height){
        this.bgp = bgp;
        this.width = width;
        this.height = height;
        this.deliveryMan = deliveryman;

        initPanel();

    }

    /**
     * This method builds part of the main panel.
     * It adds the pickup point login fields and the "View Packages" button
     * @return The panel containing the login fields and the button
     */

    private void initPanel(){

        setLayout(new BorderLayout());

        createAndRefreshPickupPointsList();

        add(buttonPanel(), BorderLayout.CENTER);
        viewPackage.addActionListener(this);
        pickupPointIdSelector.addActionListener(this);



    }

    /**
     * This method gets all the existing pickup points
     * and puts them in an interactive list
     * @return The JComboBox containing the pickup points list
     */

    private JComboBox pickupPoints(){
        try {
            ArrayList<String> strings = new ArrayList<>();
            strings.addAll(deliveryMan.getPickupPointsID());
            pickupPointIdSelector = new JComboBox(strings.toArray(new String[0]));

            return pickupPointIdSelector;

        }catch (Exception e){

            return pickupPointIdSelector;

        }
    }

    /**
     * This method creates the panel used to view
     * the packages carried by the deliveryman and
     * other details
     * @return The panel containing the button
     */

    private JPanel buttonPanel(){

        JPanel buttonPanel = new JPanel();
        JPanel button = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height/10,height/50,height/10,height/50));
        button.setLayout(new GridLayout(2 ,1));
        setMessage(buttonPanel);
        setButton(button);
        buttonPanel.add(button);
        return buttonPanel;

    }

    /**
     * This method builds the panel containing the
     * "View Packages" button
     * @param buttonPanel The panel itself
     */

    private void setButton(JPanel buttonPanel){

       viewPackage = new JButton("View Packages");
       viewPackage.setFont(new Font("",Font.BOLD,height/30));

       viewPackage.setBackground(Color.orange);
       viewPackage.setFocusable(false);

       logOut = new JButton("LogOut");
       logOut.setFont(new Font("",Font.BOLD,height/30));

       logOut.setBackground(Color.orange);
       logOut.setFocusable(false);

       logOut.addActionListener(this);

        buttonPanel.add(viewPackage);
        buttonPanel.add(logOut);

    }

    /**
     * This method builds the label that contains
     * information about the pickup points selector
     * @param buttonPanel The panel containing the confirmation button
     */

    private void setMessage(JPanel buttonPanel){

        instructionLabel = new JLabel("<html> <center> Selezionare l ' Id del punto di ritiro</html>");
        Font font = new Font("Arial" ,ITALIC , height/25);
        instructionLabel.setBorder(BorderFactory.createTitledBorder(instructionLabel.getBorder(),SetDMLanguage.getInstance().setLoginPanel()[7] , ITALIC , 0, font, Color.red));
        instructionLabel.setVisible(true);
        buttonPanel.add(instructionLabel);

    }

    /**
     * This method shows a new panel if the "View Packages" is
     * pressed or sends the credentials to the selected pickup point
     * @param e The event that triggers the listener
     */

    public void actionPerformed(ActionEvent e) {
        String string = e.getActionCommand();
        if (string.equals(viewPackage.getActionCommand())){
            bgp.changePanel("packagePanel");
        }
        else if (string.equals(logOut.getActionCommand())){
            bgp.changePanel("loginPanel");
        }
        else {
            try {
                deliveryMan.sendCredentials((String)pickupPointIdSelector.getSelectedItem());
                refreshPickupPointsList();
            }catch (IOException exception){
                System.out.println("errore passaggio id classe startingpanel");
            }
        }
    }

    private void refreshPickupPointsList(){
        removeAll();
        initPanel();
        revalidate();

    }


    private void createAndRefreshPickupPointsList() {
        subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());

        JPanel centeredPanel = new JPanel();
        JButton refreshButton = new JButton("@");

        centeredPanel.add(refreshButton);

        subPanel.add(centeredPanel, BorderLayout.NORTH);

        Component piPoList = pickupPoints();
        subPanel.add(piPoList, BorderLayout.SOUTH);

        add(subPanel, BorderLayout.NORTH);

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                remove(subPanel);
                createAndRefreshPickupPointsList();
                revalidate();
            }
        };

        refreshButton.addActionListener(buttonListener);
    }

}
