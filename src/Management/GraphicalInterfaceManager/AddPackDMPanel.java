package Management.GraphicalInterfaceManager;


import DeliveryManSystem.DeliveryMan;
import Management.Manager;
import ObserverPattern.Observer;
import LockerSystem.Package;
import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPackDMPanel extends JPanel implements Observer {

    private Manager manager;
    private JComboBox boxDM = new JComboBox();
    private JComboBox boxPack = new JComboBox();
    private JTextArea areaInfo = new JTextArea();

    public AddPackDMPanel(Manager manager){
        this.manager = manager;
        manager.addObserver(this);
        initPanel();
    }

    public void initPanel(){
        update();
        JLabel delID = new JLabel("Delivery Man ID: ");
        JLabel packID = new JLabel("Pack ID: ");
        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(boxDM.getItemCount()>0 && boxPack.getItemCount()>0) {
                    String delID = boxDM.getSelectedItem().toString();
                    String packID = boxPack.getSelectedItem().toString();
                    manager.addPackageToDeliveryMan(delID, packID);
                    areaInfo.setText("Pack "+packID+" added to the Delivery Man "+delID);
                } else {
                    ErrorGUIMain guiError = new ErrorGUIMain("No object!", true);
                }
            }
        });

        JPanel p1 = new JPanel();
        p1.add(delID);  p1.add(boxDM);

        JPanel p2 = new JPanel();
        p2.add(packID); p2.add(boxPack);

        JPanel p3 = new JPanel();
        p3.add(p1); p3.add(p2); p3.add(buttonConfirm);

        setLayout(new BorderLayout());
        add(p3,BorderLayout.NORTH); add(areaInfo, BorderLayout.CENTER);
    }

    public void updateBox(){
        boxDM.removeAllItems();
        for(DeliveryMan del : manager.getDeliveryMenList()) {
            boxDM.addItem(del.getId());
        }
        boxPack.removeAllItems();
        for(Package pack : manager.getUnassignedPackagesList()) {
            boxPack.addItem(pack.getId());
        }
    }

    @Override
    public void update() {
        updateBox();
    }
}
