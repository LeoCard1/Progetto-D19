package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartingPanel extends JPanel implements ActionListener {

    private Frame frame;
    private DeliveryMan deliveryMan;
    private JButton sendCredentials, viewPackage;
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

       //settings buttonPanel

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,height/50,height/10,height/50));

        //setMessageError(buttonPanel);
        setButton(buttonPanel);

        //add to background panel

        panelContainer.add(pickupPoints());
        panelContainer.add(buttonPanel);
        add(panelContainer);

        setAction();

    }

    private JComboBox pickupPoints(){

        try {

            JComboBox pickupPointId = new JComboBox();
            return pickupPointId;

        }catch (Exception e){

            return pickupPoints();

        }


    }

    private void setButton(JPanel buttonPanel){

        //new button

        sendCredentials = new JButton("Send Credentials");
        viewPackage = new JButton("View Package");

        //settings button

        sendCredentials.setBackground(Color.orange);
        sendCredentials.setFocusable(false);
        viewPackage.setBackground(Color.orange);
        viewPackage.setFocusable(false);

        //add button

        buttonPanel.add(sendCredentials);
        buttonPanel.add(viewPackage);

    }

    private void setAction(){

        sendCredentials.addActionListener(this);
        viewPackage.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        String string = e.getActionCommand();

        if (string.equals(viewPackage.getActionCommand())){

            newFrame();

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
