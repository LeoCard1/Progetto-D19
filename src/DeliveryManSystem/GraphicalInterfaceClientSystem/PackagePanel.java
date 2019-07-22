package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This Class creates the panel showing a
 * deliveryman's packages and their destination
 * @author Zappa Roberto
 * @version 1.0.1
 */

public class PackagePanel extends JPanel {

    private DeliveryMan deliveryMan;
    private int height;
    private BackgroundPanel bgp;

    /**
     * The constructor
     * @param deliveryman The deliveryman who's logged in the system
     * @param height Screen height
     */

    PackagePanel(BackgroundPanel bgp, DeliveryMan deliveryman, int height){
        this.bgp = bgp;
        this.height = height;
        this.deliveryMan = deliveryman;
        initPanel();

    }

    /**
     * This method initializes the PackagePanel
     */

    private void initPanel(){
        setOpaque(false);
        setLayout(new GridLayout(3,1 ));

        add(deliveries());
        add(deliveriesExpired());
        add(buttonPanel());


    }

    /**
     * This method creates a button, used to
     * cycle to the previous panel, and the panel
     * surrounding it
     * @return The button that's just been created
     */

    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height/50,height/50,height/12,height/50));
        buttonPanel.add(getButtonInfoPickupPoint());
        buttonPanel.add(getButtonBack());
        return buttonPanel;
    }

    /**
     * This method create a jPanel and adds to it the jScrollTable
     * @param table created by deliveries and deliveriesExpired methods
     * @return the jPanel that's just been created
     */

    private JPanel jScrollTable(JTable table){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
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

    private JPanel deliveries() {
        JPanel panelDel = createPanelDeliveries(SetDMLanguage.getInstance().setPackagePanel()[0]);
        try {
            panelDel.add(jScrollTable(buildTable(deliveryMan.getDeliveries())), BorderLayout.CENTER);
        } catch (IOException e) {
            panelDel.add(jScrollTable(getEmptyTable()), BorderLayout.CENTER);
        }
        return panelDel;
    }

    private JPanel createPanelDeliveries(String text){
        JPanel panelDel = new JPanel();
        panelDel.setOpaque(false);
        panelDel.setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder(text);
        border.setTitleFont(new Font("Bold" ,Font.BOLD , height/30));
        border.setTitleColor(Color.WHITE);
        panelDel.setBorder(border);
        return panelDel;
    }

    /**
     * This panel gets all the packages that must
     * be collected by the logged deliveryman from the
     * main server and shows them in table format
     * @return The panel containing the table
     */

    private JPanel deliveriesExpired() {
        JPanel panelDelExp = createPanelDeliveries(SetDMLanguage.getInstance().setPackagePanel()[1]);
        try {
            panelDelExp.add(jScrollTable(buildTable(deliveryMan.getDeliveriesExpired())), BorderLayout.CENTER);
        } catch (IOException e) {
            panelDelExp.add(jScrollTable(getEmptyTable()), BorderLayout.CENTER);
        }
        return panelDelExp;
    }

    /**
     * This method creates the actual table. It's used for
     * both a deliveryman's pending deliveries and for
     * the packages he should collect
     * @param delTabList Either deliveries or expired packages
     * @return The table
     */

    private JTable buildTable(ArrayList<DeliveryTable> delTabList) {

        String[] tableParameters = {SetDMLanguage.getInstance().setPackagePanel()[2],
                                    SetDMLanguage.getInstance().setPackagePanel()[3]};

        String[][] table = new String[delTabList.size()][];
        int i = 0;
        for (DeliveryTable e : delTabList){
            String[] p = {e.getPipoID() , e.getPackID()};
            table[i] = p;
            i++;
        }

        JTable finalTable = new JTable(table, tableParameters);

        finalTable.setGridColor(Color.red);
        finalTable.setEnabled(false);

        return finalTable;
    }

    /**
     * This method creates an empty JTable
     * @return the created empty JTable
     */

    private JTable getEmptyTable() {
        String[] tableParameters = {SetDMLanguage.getInstance().setPackagePanel()[2],
                                    SetDMLanguage.getInstance().setPackagePanel()[3]};
        String[][] table = new String[0][];
        return new JTable(table,tableParameters);
    }

    /**
     * This method creates the button used to cycle
     * back to the previous panel
     * @return The button
     */

    private JButton getButtonBack(){

        JButton back = new JButton(SetDMLanguage.getInstance().setPackagePanel()[4]);
        back.setBackground(new Color(255,153,0));
        back.setFocusable(false);
        back.setFont(new Font("", Font.BOLD, height/30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("startingPanel");
            }
        });

        return back;

    }

    private JButton getButtonInfoPickupPoint(){
        JButton info = new JButton(SetDMLanguage.getInstance().setPackagePanel()[5]);
        info.setBackground(new Color(255,153,0));
        info.setFocusable(false);
        info.setFont(new Font("", Font.BOLD, height/30));
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("pickupPointInfoPanel");
            }
        });

        return info;

    }

    /**
     * This method refreshes the package list
     */

    public void refreshPackageList() {
        removeAll();
        initPanel();
        revalidate();

    }



}
