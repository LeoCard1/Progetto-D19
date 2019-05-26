package Management.GraphicalInterfaceManager;


import Management.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDMPanel extends JPanel {

    private Manager manager;
    private JTextField insertID = new JTextField();
    private JTextArea infoArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane();
    private JLabel insertIDLabel = new JLabel("Insert ID: ");
    private JButton buttonConfirm = new JButton("Confirm");

    public NewDMPanel(Manager manager){
        this.manager = manager;
        initPanel();
    }

    public void initPanel(){
        scrollPane.setViewportView(infoArea);
        infoArea.setEditable(false);
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String delID = insertID.getText();
                if( manager.createDeliveryMan(delID)) {
                    String text = "DeliveryMan added succesfully\nID: " + delID +"\nPassword: " +manager.getDeliveryMan(delID).getPassword();
                    infoArea.setText(text);
                }
            }
        });
        orderComponents();
    }

    public void orderComponents(){
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,2));
        p1.add(insertIDLabel); p1.add(insertID);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2,1));
        p2.add(p1);  p2.add(buttonConfirm);

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(p2, BorderLayout.NORTH);

        JPanel p31 = new JPanel();
        p31.setLayout(new BorderLayout());
        p31.add(p3,BorderLayout.WEST);  p31.add(new JPanel(),BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(p31,BorderLayout.WEST);  add(scrollPane, BorderLayout.CENTER);
    }
}
