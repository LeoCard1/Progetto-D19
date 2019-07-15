package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Font.ITALIC;

public class StartingPanel extends JPanel implements ActionListener {

    private DeliveryMan deliveryMan;
    private JButton viewPackage;
    private JLabel instructionLabel;
    private JComboBox pickupPointIdSelector;
    private JPanel cardContainer;
    private int width;
    private int height;

    StartingPanel( JPanel cardContainer, DeliveryMan deliveryman, int width, int height){

        this.width = width;
        this.height = height;
        this.deliveryMan = deliveryman;
        this.cardContainer = cardContainer;

    }

    /**
     * This method builds part of the main panel.
     * It adds the pickup point id fields and the views package button
     * @return The panel containing the pickup point id fields and the button
     */

    JPanel startingPanelCard(){

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2,1 ));
        panelContainer.add(pickupPoints());
        panelContainer.add(buttonPanel());
        viewPackage.addActionListener(this);
        pickupPointIdSelector.addActionListener(this);
        return panelContainer;

    }

    /**
     * This method creates the The JCombobox
     * whit related the id of pickup point
     * @return JCombobox
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
     * This method creates the view package button
     * and related details
     * @return The panel containing the button
     */

    private JPanel buttonPanel(){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height/10,height/50,height/10,height/50));
        setMessage(buttonPanel);
        setButton(buttonPanel);
        return buttonPanel;

    }

    /**
     *  This method builds the panel containing the
     *  View Package button
     * @param buttonPanel The panel containing the view package button
     */

    private void setButton(JPanel buttonPanel){

       viewPackage = new JButton("View Package");

       viewPackage.setBackground(Color.orange);
       viewPackage.setFocusable(false);

       buttonPanel.add(viewPackage);

    }

    /**
     * This method builds the label that contains information about the pickup points selector
     * @param buttonPanel The panel containing the confirmation button
     */

    private void setMessage(JPanel buttonPanel){

        instructionLabel = new JLabel("Selezionare l ' Id del punto di ritiro");
        Font font = new Font("Arial" ,ITALIC , 15);
        instructionLabel.setBorder(BorderFactory.createTitledBorder(instructionLabel.getBorder(),SetDMLanguage.getInstance().setLoginPanel()[7] , ITALIC , 0, font, Color.red));
        instructionLabel.setVisible(true);
        buttonPanel.add(instructionLabel);

    }

    /**
     * This method shows a new panel if the view Package is pushed or
     *  send credentials to the selected pickup point
     * @param e The event that triggers the listener
     */

    public void actionPerformed(ActionEvent e) {

        String string = e.getActionCommand();

        if (string.equals(viewPackage.getActionCommand())){

            nextCard();

        }

        else {
            try {

                deliveryMan.sendCredentials((String)pickupPointIdSelector.getSelectedItem());

            }catch (IOException exception){

                System.out.println("errore passaggio id classe startingpanel");

            }
        }
    }

    /**
     * cycles to the next one
     */

    private void nextCard(){

        CardLayout cl = (CardLayout) cardContainer.getLayout();
        cl.next(cardContainer);

    }



}
