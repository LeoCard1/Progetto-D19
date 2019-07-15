package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This Class creates the panel showing a
 * deliveryman's packages and their destination
 * @author Gruppo D19
 * @version 1.0.1
 */

public class PackagePanel extends JPanel implements ActionListener {

    private JPanel cardContainer;
    private DeliveryMan deliveryMan;
    private JButton back;
    private int width;
    private int height;

    /**
     * The constructor
     * @param cardContainer The main panel. This panel is added to the former
     * @param deliveryman The deliveryman who's logged in the system
     * @param width Screen width
     * @param height Screen height
     */

    PackagePanel(JPanel cardContainer, DeliveryMan deliveryman, int width, int height){

        this.width = width;
        this.height = height;
        this.deliveryMan = deliveryman;
        this.cardContainer = cardContainer;

    }

    /**
     * This method builds the panel
     * @return The panel that's just been created
     */

    public JPanel packagePanelCard(){

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(3,1 ));

        panelContainer.add(jScrollTable(deliveries()));
        panelContainer.add(jScrollTable(deliveriesExpired()));
        panelContainer.add(buttonPanel());

        back.addActionListener(this);
        return panelContainer;

    }

    /**
     * This method creates a button, used to
     * cycle to the previous panel, and the panel
     * surrounding it
     * @return The button that's just been created
     */

    private JPanel buttonPanel(){

        JPanel buttonPanel = new JPanel(new GridLayout(1,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height/10,height/50,height/10,height/50));
        buttonPanel.add(setButton());
        return buttonPanel;

    }

    private JPanel jScrollTable(JTable table){

        JPanel panel = new JPanel();
        JScrollPane scrollPanel = new JScrollPane(table);
        panel.add(scrollPanel , BorderLayout.CENTER);
        return panel;

    }

    /**
     * This panel gets all the deliveries that must
     * be made by the logged deliveryman from the
     * main server and shows them in table format
     * @return The panel containing the table
     */

    private JTable deliveries() {

        try {
            return buildTable(deliveryMan.getDeliveries());
        } catch (IOException e) {
           return new JTable();
        }

    }

    /**
     * This panel gets all the packages that must
     * be collected by the logged deliveryman from the
     * main server and shows them in table format
     * @return The panel containing the table
     */

    private JTable deliveriesExpired() {

        try {
            return buildTable(deliveryMan.getDeliveriesExpired());
        } catch (IOException e) {
            return new JTable();
        }

    }

    /**
     * This method creates the actual table. It's used for
     * both a deliveryman's pending deliveries and for
     * the packages he should collect
     * @param delTabList Either deliveries or expired packages
     * @return The table
     */

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

    /**
     * This method creates the button used to cycle
     * back to the previous panel
     * @return The button
     */

    private JButton setButton(){

        back = new JButton("Go back");
        back.setBackground(Color.orange);
        back.setFocusable(false);
        back.setFont(new Font("", Font.BOLD, height/30));

        return back;
    }

    /**
     * The panel's action listener, used to cycle to
     * the previous panel
     * @param e The event that triggers the listener
     */

    public void actionPerformed(ActionEvent e) {

        previousCard();
    }

    /**
     * The method used to cycle to the previous panel
     */

    private void previousCard(){

        CardLayout cl = (CardLayout)cardContainer.getLayout();
        cl.previous(cardContainer);
        update();
    }

    private void update(){

        deliveries();
        deliveriesExpired();
        revalidate();

    }

}
