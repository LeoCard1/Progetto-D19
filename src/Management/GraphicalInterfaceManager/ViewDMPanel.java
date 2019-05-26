package Management.GraphicalInterfaceManager;

import DeliveryManSystem.DeliveryMan;
import Management.Manager;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDMPanel extends JPanel implements Observer {

    private Manager manager;
    private JComboBox boxDM = new JComboBox();
    private JScrollPane scrollPane = new JScrollPane();
    private JTextArea infoPack = new JTextArea();
    private JTextField infoDM = new JTextField();
    private JLabel descriptionPack = new JLabel("packID          height             length              width");
    private JLabel password = new JLabel("Password");
    private JLabel select = new JLabel("Select DM: ");
    private JButton buttonConfirm = new JButton("Confirm");

    public ViewDMPanel(Manager manager){
        this.manager= manager;
        manager.addObserver(this);
        initPanel();
    }

    public void initPanel(){
        update();
        scrollPane.setViewportView(infoPack);
        infoPack.setEditable(false);
        infoDM.setEditable(false);
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateText();
            }
        });
        orderComponents();
    }

    public void orderComponents(){
        JPanel p0 = new JPanel();
        p0.setLayout(new GridLayout(1,2));
        p0.add(select); p0.add(boxDM);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,1));
        p1.add(p0); p1.add(buttonConfirm);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2,1));
        p2.add(password);  p2.add(infoDM);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(2,1));
        p3.add(p1);  p3.add(p2);

        JPanel p4 = new JPanel();
        p4.setLayout(new BorderLayout());
        p4.add(p3,BorderLayout.NORTH);

        JPanel p41 = new JPanel();
        p41.setLayout(new BorderLayout());
        p41.add(p4, BorderLayout.WEST);  p41.add(new JPanel(), BorderLayout.CENTER);

        JPanel p5 = new JPanel();
        p5.setLayout(new BorderLayout());
        p5.add(descriptionPack,BorderLayout.NORTH); p5.add(scrollPane,BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(p41, BorderLayout.WEST);  add(p5, BorderLayout.CENTER);
    }

    public void updateText(){
        if(boxDM.getItemCount()>0) {
            String delID = boxDM.getSelectedItem().toString();
            DeliveryMan del = manager.getDeliveryMan(delID);
            infoPack.setText(del.packageListToString());
            infoDM.setText(del.getPassword());
        }
    }

    @Override
    public void update() {
        boxDM.removeAllItems();
        for(DeliveryMan del : manager.getDeliveryMenList()) {
            boxDM.addItem(del.getId());
        }
        updateText();
    }
}
