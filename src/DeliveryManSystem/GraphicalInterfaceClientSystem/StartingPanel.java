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

    private Frame frame;
    private DeliveryMan deliveryMan;
    private JButton sendCredentials, viewPackage;
    private JLabel instructionLabel;
    private JComboBox pickupPointIdSelector;
    private int width;
    private int height;

    StartingPanel(Frame frame, DeliveryMan deliveryman, int width, int height){

        this.width = width;
        this.height = height;
        this.deliveryMan = deliveryman;
        this.frame = frame;
        setListPanel();

    }

    private void setListPanel(){

        //settings panelBackground

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black,height/80 ));

        //settings panelContainer

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2,1 ));
        panelContainer.add(pickupPoints());
        panelContainer.add(buttonPanel());
        add(panelContainer);

        setAction();

    }

    private JPanel buttonPanel(){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,height/50,height/10,height/50));
        setMessage(buttonPanel);
        setButton(buttonPanel);
        return buttonPanel;

    }

    private JComboBox pickupPoints(){

       try {

            ArrayList<String> stringhe = new ArrayList<>();
            stringhe.addAll(deliveryMan.getPickupPointsID());
            pickupPointIdSelector = new JComboBox(stringhe.toArray(new String[0]));
            return pickupPointIdSelector;

        }catch (Exception e){

            return pickupPointIdSelector;

        }
    }

    private void setButton(JPanel buttonPanel){

        //new button


        viewPackage = new JButton("View Package");

        //settings button

        viewPackage.setBackground(Color.orange);
        viewPackage.setFocusable(false);

        //add button

        buttonPanel.add(viewPackage);

    }

    private void setMessage(JPanel buttonPanel){

        instructionLabel = new JLabel("Selezionare l ' Id del punto di ritiro");
        Font font = new Font("Arial" ,ITALIC , 15);
        instructionLabel.setBorder(BorderFactory.createTitledBorder(instructionLabel.getBorder(),SetDMLanguage.getInstance().setLoginPanel()[7] , ITALIC , 0, font, Color.red));
        instructionLabel.setVisible(false);
        buttonPanel.add(instructionLabel);


    }

    private void setAction(){

        viewPackage.addActionListener(this);
        pickupPointIdSelector.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        String string = e.getActionCommand();

        if (string.equals(viewPackage.getActionCommand())){

            newFrame();

        }

        else {
            try {

                deliveryMan.sendCredentials((String)pickupPointIdSelector.getSelectedItem());

            }catch (IOException exception){

                System.out.println("errore passaggio id classe startingpanel");


            }

        }



    }

    private void newFrame(){

        frame.remove(this);
        frame.add(new PackagePanel(frame, deliveryMan, width, height));
        frame.repaint();
        frame.validate();
        frame.setVisible(false);
        frame.setVisible(true);

    }



}
