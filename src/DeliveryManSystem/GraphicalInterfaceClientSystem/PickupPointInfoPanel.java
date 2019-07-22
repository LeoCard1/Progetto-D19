package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DatabaseSystem.PersistenceFacade;
import DeliveryManSystem.DatabaseSystem.Tables.PickupPointTable;
import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeSet;

/**
 * This class represents the panel containing information on
 * pickup points
 * @author Andrea Stella
 * @version 1.0.0
 */

public class PickupPointInfoPanel extends JPanel {

    private BackgroundPanel bgp;
    private DeliveryMan deliveryMan;
    private int height;

    /**
     * The constructor
     * @param bgp The BackGroundPanel
     * @param deliveryman The deliveryman who's logged in the system
     * @param height Screen height
     */

    public PickupPointInfoPanel(BackgroundPanel bgp, DeliveryMan deliveryman, int height){
        this.bgp = bgp;
        this.deliveryMan = deliveryman;
        this.height = height;
        initPanel();
    }

    /**
     * This method initializes the JPanel by inserting into it the
     * centeredPanel and the buttonPanel
     */

    private void initPanel(){
        setOpaque(false);
        setLayout(new GridLayout(2,1));
        add(getCenteredPanel());
        add(getButtonPanel());
    }

    /**
     * This method creates a JPanel containing the back button
     * @return the created JPanel
     */

    private JPanel getButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height/4,height/50,height/12,height/50));
        buttonPanel.add(getButtonBack(), BorderLayout.CENTER);
        return buttonPanel;
    }

    /**
     * This method creates the back button. When the back button
     * is clicked, the panel changes to package panel
     * @return the created back button
     */

    private JButton getButtonBack(){
        JButton back = new JButton(SetDMLanguage.getInstance().setPickupPointInfoPanel()[4]);
        back.setBackground(new Color(255,153,0));
        back.setFocusable(false);
        back.setFont(new Font("", Font.BOLD, height/30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("packagePanel");
            }
        });
        return back;
    }

    /**
     * This method creates the JPanel containing the pickup points
     * information JTable
     * @return the created JPanel
     */

    private JPanel getCenteredPanel(){
        JPanel centeredPanel = new JPanel();
        centeredPanel.setOpaque(false);
        centeredPanel.setLayout(new BorderLayout());
        JScrollPane scrollPanel = new JScrollPane(getTable());
        TitledBorder border = BorderFactory.createTitledBorder(SetDMLanguage.getInstance().setPickupPointInfoPanel()[0]);
        border.setTitleFont(new Font("Bold" ,Font.BOLD , height/30));
        border.setTitleColor(Color.WHITE);
        centeredPanel.setBorder(border);
        centeredPanel.add(scrollPanel , BorderLayout.CENTER);
        return centeredPanel;
    }

    /**
     * This method creates the JTable containing pickup points information
     * @return the created JTable
     */

    private JTable getTable(){
        String[] tableParameters = {SetDMLanguage.getInstance().setPickupPointInfoPanel()[1],
                                    SetDMLanguage.getInstance().setPickupPointInfoPanel()[2],
                                    SetDMLanguage.getInstance().setPickupPointInfoPanel()[3]};

        try {
            TreeSet<String> pickupPoints = deliveryMan.getPickupPointsID();
            String[][] table = new String[pickupPoints.size()][];
            int i = 0;
            for (String id : pickupPoints) {
                PersistenceFacade facade = new PersistenceFacade();
                PickupPointTable pickupPointTable = facade.getPickupPoint(id);
                String[] p = {id, pickupPointTable.getLocation(), pickupPointTable.getAddress()};
                table[i] = p;
                i++;
            }
            JTable finalTable = new JTable(table, tableParameters);

            finalTable.setGridColor(Color.red);
            finalTable.setEnabled(false);

            return finalTable;

        }catch (IOException e) {
            return getEmptyTable();
        }
    }

    /**
     * This method creates an empty JTable
     * @return the created empty JTable
     */

    private JTable getEmptyTable() {
        String[] tableParameters = {SetDMLanguage.getInstance().setPickupPointInfoPanel()[1],
                                    SetDMLanguage.getInstance().setPickupPointInfoPanel()[2],
                                    SetDMLanguage.getInstance().setPickupPointInfoPanel()[3]};
        String[][] table = new String[0][];
        return new JTable(table,tableParameters);
    }

    /**
     * This method refresh the pickup points list
     */

    public void refreshPickupPointList() {
        removeAll();
        initPanel();
        revalidate();
    }
}
