package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DatabaseSystem.Tables.DeliveryTable;
import DeliveryManSystem.DeliveryMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
        setAction();
        return panelContainer;

    }

    private JPanel buttonPanel(){

        JPanel buttonPanel = new JPanel(new GridLayout(1,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height/10,height/50,height/10,height/50));
        buttonPanel.add(setButton());
        return buttonPanel;

    }

    private JTable deliveries(){

        String[] prova = {"Id Pipo" , "Id Pacco" };

        try {

            String[][] table = new String[deliveryMan.getDeliveries().size()][];
            int i = 0;
            for (DeliveryTable e : deliveryMan.getDeliveries() ){

                String[] p = {e.getPipoID() , e.getPackID()};
                table[i] = p;
                i++;

            }

            JTable tabella = new JTable(table,prova);
            tabella.setGridColor(Color.green);
            return tabella;

        }catch (IOException e){

           return new JTable();

        }
    }

    private JTable deliveriesExpired(){

        String[] prova = {"Id Pipo" , "Id Pacco" };
        try {

            String[][] table = new String[deliveryMan.getDeliveriesExpired().size()][];
            int i = 0;
            for (DeliveryTable e : deliveryMan.getDeliveriesExpired() ){

                String[] p = {e.getPipoID() , e.getPackID()};
                table[i] = p;
                i++;

            }

            JTable tabella = new JTable(table,prova);
            tabella.setGridColor(Color.red);

            return tabella;

        }catch (IOException e){

            return new JTable();

        }
    }

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

        previousCard();

    }

    private void previousCard(){

        CardLayout cl = (CardLayout) cardContainer.getLayout();
        cl.previous(cardContainer);


    }

}
