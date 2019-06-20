package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import PickupPointSystem.LoginDelMan;
import PickupPointSystem.ObserverPattern.Observer;
import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class LoginDelManPanel extends JPanel {

    private PickupPoint pickupPoint;
    private BackGroundPanel bgp;
    private JTextField delID = new JTextField();
    private JPasswordField password = new JPasswordField();
    private LoginDelMan loginDelMan;
    private int height;
    private int width;

    public LoginDelManPanel(PickupPoint pickupPoint, BackGroundPanel bgp){
        this.pickupPoint = pickupPoint;
        loginDelMan = new LoginDelMan(pickupPoint);
        this.bgp = bgp;
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;
        initPanel();
    }

    private void initPanel(){
        setPreferredSize(new Dimension(width*2/3, height*2/3));
        setLayout(new BorderLayout());
        add(createBackPanel(), BorderLayout.NORTH);
        add(createLoginPanel(), BorderLayout.CENTER);
    }

    private JPanel createLoginPanel(){
        JLabel insertCred = new JLabel("Insert Your Credentials");
        insertCred.setFont(new Font("", Font.PLAIN, 24));
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3,1));
        loginPanel.add(insertCred);
        loginPanel.add(createCredentialsPanel());
        loginPanel.add(createConfirmButton());
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, height/8));
        p.add(loginPanel);
        return p;
    }

    private JPanel createCredentialsPanel(){
        JLabel idLabel = new JLabel("id: ");
        idLabel.setFont(new Font("", Font.PLAIN, 20));
        JLabel passwordLabel = new JLabel("password: ");
        passwordLabel.setFont(new Font("", Font.PLAIN, 20));
        JPanel credPanel = new JPanel();
        credPanel.setLayout(new GridLayout(2,2));
        delID.setFont(new Font("", Font.PLAIN, 15));
        password.setFont(new Font("", Font.PLAIN, 15));
        credPanel.add(idLabel);
        credPanel.add(delID);
        credPanel.add(passwordLabel);
        credPanel.add(password);
        return credPanel;
    }

    private JButton createConfirmButton(){
        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginDelMan.login(delID.getText(), password.getText())){
                    try {
                        loginDelMan.addDeliverymanPackages();
                        loginDelMan.getPackagesToPickup();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    bgp.changePanel("viewBoxesPanel");
                } else new ErrorGUIMain("Wrong username or password", false);
                deleteText();
            }

        });
        return buttonConfirm;
    }

    private JPanel createBackPanel(){
        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("boxAccessPanel");
                deleteText();
            }
        });
        JPanel backpanel = new JPanel();
        backpanel.setLayout(new BorderLayout());
        backpanel.add(buttonBack, BorderLayout.WEST);
        return backpanel;
    }

    private void deleteText(){
        password.setText("");
        delID.setText("");
    }

}
