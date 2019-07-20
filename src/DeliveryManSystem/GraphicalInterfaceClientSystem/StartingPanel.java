package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;
import DeliveryManSystem.Exceptions.PickupPointServerUnavailableException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Font.ITALIC;
import static java.awt.Toolkit.getDefaultToolkit;

/**
 * This Class creates the main panel used in the PDA
 * @author Gruppo D19
 * @version 1.0.2
 */

public class StartingPanel extends JPanel {

    private DeliveryMan deliveryMan;
    private JButton viewPackage;
    private JButton logOut;
    private AlertLabel alertLabel;
    private JComboBox pickupPointIdSelector;
    private BackgroundPanel bgp;
    private int height;
    private JPanel subPanel;

    /**
     * The constructor
     * @param bgp
     * @param deliveryman
     * @param height
     */

    StartingPanel( BackgroundPanel bgp, DeliveryMan deliveryman, int height){
        this.bgp = bgp;
        this.height = height;
        this.deliveryMan = deliveryman;

        initPanel();

    }

    /**
     * This method builds part of the main panel.
     * It adds the pickup point login fields and the "View Packages" button
     */

    private void initPanel(){

        setLayout(new BorderLayout());

        add(buttonPanel(), BorderLayout.CENTER);

        createAndRefreshPickupPointsList();

      

    }

    /**
     * This method gets all the existing pickup points
     * and puts them in an interactive list
     * @return The JComboBox containing the pickup points list
     */

    private JComboBox pickupPoints(){

            ArrayList<String> strings = new ArrayList<>();
        try {
            strings.addAll(deliveryMan.getPickupPointsID());
        } catch (IOException e) {
            alertLabel.setTextAndIcon("<html> <center> Impossibile connettersi al server</html>", true);
        }
        pickupPointIdSelector = new JComboBox(strings.toArray(new String[0]));

            pickupPointIdSelector.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        if(pickupPointIdSelector.getSelectedItem()!=null) {
                            deliveryMan.sendCredentials((String) pickupPointIdSelector.getSelectedItem());
                            refreshPickupPointsList();
                            alertLabel.showMessageForAFewSeconds("<html><center>Credentials sent succesfully</html>", false);
                        }
                    } catch (IOException e) {
                        alertLabel.setTextAndIcon("<html> <center> Impossibile connettersi al server</html>", true);
                    } catch (PickupPointServerUnavailableException p){
                        alertLabel.showMessageForAFewSeconds("<html> <center> Impossibile connettersi al server del PickupPoint</html>", true);
                    }
                }
            });

            return pickupPointIdSelector;


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
       viewPackage.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               bgp.changePanel("packagePanel");
           }
       });
       viewPackage.setFont(new Font("",Font.BOLD,height/30));

       viewPackage.setBackground(Color.orange);
       viewPackage.setFocusable(false);

       logOut = new JButton("LogOut");
       logOut.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               buttonPanel.remove(logOut);
               buttonPanel.add(getConfirmPanel(buttonPanel));
               revalidate();
           }
       });
       logOut.setFont(new Font("",Font.BOLD,height/30));

       logOut.setBackground(Color.orange);
       logOut.setFocusable(false);


        buttonPanel.add(viewPackage);
        buttonPanel.add(logOut);
    }

    /**
     * This method creates the panel containing the request for
     * confirmation of exit
     * @param panelCont the panel that will containing the confirm panel
     * @return the panel that has been created
     */

    public JPanel getConfirmPanel(JPanel panelCont){
        JPanel confirmPanel = new JPanel();
        JLabel areYouSureLabel = new JLabel("Are You Sure?");
        areYouSureLabel.setFont(new Font("Arial", Font.BOLD, height/30));
        JButton buttonYes = new JButton("YES");
        buttonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("loginPanel");
            }
        });
        JButton buttonNo = new JButton("NO");
        buttonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCont.remove(confirmPanel);
                panelCont.add(logOut);
                panelCont.repaint();
                
            }
        });
        buttonYes.setBackground(Color.ORANGE);
        buttonNo.setBackground(Color.ORANGE);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2));
        buttonsPanel.add(buttonYes);
        buttonsPanel.add(buttonNo);
        confirmPanel.setLayout(new GridLayout(2,1));
        confirmPanel.add(areYouSureLabel);
        confirmPanel.add(buttonsPanel);
        return confirmPanel;

    }

    /**
     * This method builds the label that contains
     * information about the pickup points selector
     * @param buttonPanel The panel containing the confirmation button
     */

    private void setMessage(JPanel buttonPanel){

        alertLabel = new AlertLabel("<html> <center> Selezionare l ' Id del punto di ritiro</html>", true);
        alertLabel.setDefaultTextAndIcon("<html> <center> Selezionare l ' Id del punto di ritiro</html>", true);
        Font font = new Font("Arial" ,ITALIC , height/25);
        alertLabel.setBorder(BorderFactory.createTitledBorder(alertLabel.getBorder(),SetDMLanguage.getInstance().setLoginPanel()[7] , ITALIC , 0, font, Color.red));
        buttonPanel.add(alertLabel);

    }



    /**
     * This method refresh the pickup point list
     */

    public void refreshPickupPointsList(){
        removeAll();
        initPanel();
        revalidate();

    }

    /**
     * This method create and refresh the pickup point list
     */

    private void createAndRefreshPickupPointsList() {
        subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());

        JPanel centeredPanel = new JPanel();
        JButton refreshButton = new JButton();
        Image backImage = getDefaultToolkit().createImage("src/DeliveryManSystem/GraphicalInterfaceClientSystem/Icons/update.jpg").getScaledInstance(height/10, height/10,Image.SCALE_DEFAULT);
        refreshButton.setIcon(new ImageIcon(backImage));
        refreshButton.setContentAreaFilled(false);
        refreshButton.setBorderPainted(false);


        centeredPanel.add(refreshButton);

        subPanel.add(centeredPanel, BorderLayout.NORTH);

        Component piPoList = pickupPoints();
        subPanel.add(piPoList, BorderLayout.SOUTH);

        add(subPanel, BorderLayout.NORTH);

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshPickupPointsList();
            }
        };

        refreshButton.addActionListener(buttonListener);
    }

}
