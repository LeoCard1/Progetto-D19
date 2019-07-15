package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class PackagePanel extends JPanel implements ActionListener {

    private JPanel cardContainer;
    private DeliveryMan deliveryMan;
    private JButton back;
    private int width;
    private int height;

    PackagePanel(JPanel cardContainer, DeliveryMan deliveryman, int width, int height){
        this.width = width;
        this.height = height;
        this.deliveryMan = deliveryman;
        this.cardContainer = cardContainer;
    }

    public JPanel packagePanelCard(){
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(3,1 ));

        panelContainer.add(deliveries());
        panelContainer.add(deliveriesExpired());
        panelContainer.add(buttonPanel());

        back.addActionListener(this);
        return panelContainer;
    }

    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel(new GridLayout(1,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height/10,height/50,height/10,height/50));

        buttonPanel.add(setButton());
        return buttonPanel;
    }

    private JTable deliveries(){
        try {
            return buildTable(deliveryMan.getDeliveries());
        } catch (IOException e) {
           return new JTable();
        }
    }

    private JTable deliveriesExpired(){
        try {
            return buildTable(deliveryMan.getDeliveriesExpired());
        } catch (IOException e) {
            return new JTable();
        }
    }

    private JTable buildTable(ArrayList<DeliveryTable> delTabList) {
        String[] tableParameters = {"Pickup Point ID", "Package ID"};

        String[][] table = new String[delTabList.size()][];
        int i = 0;
        for (DeliveryTable e : delTabList){
            String[] p = {e.getPipoID() , e.getPackID()};
            table[i] = p;
            i++;
        }

        JTable finalTable = new JTable(table, tableParameters);
        finalTable.setGridColor(Color.red);
        return finalTable;
    }

    private JButton setButton(){
        back = new JButton("back");
        back.setBackground(Color.orange);
        back.setFocusable(false);

        return back;
    }

    public void actionPerformed(ActionEvent e) {
        previousCard();
    }

    private void previousCard(){
        CardLayout cl = (CardLayout) cardContainer.getLayout();
        cl.previous(cardContainer);
    }
}
