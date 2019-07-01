package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PackagePanel extends JPanel implements ActionListener {

    private Frame frame;
    private DeliveryMan deliveryMan;
    private JButton back;
    private int width;
    private int height;

    PackagePanel(Frame frame, DeliveryMan deliveryman, int width, int height){

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

        //settings panelList




        //add to background panel

       // panelContainer.add(packageList());
        add(setButton(), BorderLayout.SOUTH);
        add(panelContainer);

        setAction();

    }

    /*private JTable packageList(){

        try {

            JTable packages = new JTable();

            packages.add(deliveryMan.getDeliveries());
            packages.add(deliveryMan.getDeliveriesExpired());

        }catch (IOException e){

        }



    }*/

    private JButton setButton(){

        back = new JButton("back");
        back.setBackground(Color.orange);
        back.setFocusable(false);
        return back;

    }

    private void setAction(){

        back.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        newFrame();

    }

    private void newFrame(){

        frame.remove(this);
        frame.add(new StartingPanel(frame, deliveryMan, width, height));
        frame.repaint();
        frame.validate();
        frame.setVisible(true);

    }

}
