package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.CredentialsReceiver;
import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import PickupPointSystem.GraphicalInterface.LoadingGUI.LoadingGUIMain;
import PickupPointSystem.LoginDelMan;
import PickupPointSystem.ObserverPattern.ObserverCredentials;
import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * This class is the delivery man access panel
 * @author Andrea Stella
 * @version 1.0
 */

public class LoginDelManPanel extends JPanel implements ObserverCredentials {

    private BackGroundPanel bgp;
    private JTextField delID = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton buttonConfirm;
    private AlertLabel alertLabel;
    private LoginDelMan loginDelMan;

    /**
     * The constructor. Creates an instance of loginDelMan by passing the PickupPoint
     * received to it as an argument, and adds itself as an observer to the
     * CredentialReceiver instance.
     * @param pickupPoint
     * @param bgp
     */

    public LoginDelManPanel(PickupPoint pickupPoint, BackGroundPanel bgp){
        loginDelMan = new LoginDelMan(pickupPoint);
        this.bgp = bgp;
        CredentialsReceiver credentialReceiver = CredentialsReceiver.getInstance();
        credentialReceiver.addObserver(this);

        initPanel();
    }

    /**
     * This method initialise the panel. Adds the back panel and the login panel
     */

    private void initPanel(){
        setLayout(new BorderLayout());
        add(createBackPanel(), BorderLayout.NORTH);
        add(createLoginPanel(), BorderLayout.CENTER);
    }

    /**
     * This method creates the login panel by inserting into it:
     * -the insert credentials label
     * -the credentials panel
     * -the confirm button
     * -the alert label
     * @return the panel that has been created
     */

    private JPanel createLoginPanel(){
        alertLabel = new AlertLabel("Correct Credentials", "Wrong Credentials");
        JLabel insertCred = new JLabel("Insert Your Credentials");
        insertCred.setFont(new Font("", Font.PLAIN, 24));
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4,1));
        loginPanel.add(insertCred);
        loginPanel.add(createCredentialsPanel());
        loginPanel.add(createConfirmButton());
        loginPanel.add(alertLabel);
        Toolkit tk = getDefaultToolkit();
        int height = tk.getScreenSize().height;
        loginPanel.setOpaque(false);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, height/8));
        p.setOpaque(false);
        p.add(loginPanel);
        return p;
    }

    /**
     * This class creates the credentials panel by inserting into it:
     * -the id label
     * -the id textfield
     * -the password label
     * -the password textfield
     * @return the panel that has been created
     */

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
        credPanel.setOpaque(false);
        return credPanel;
    }

    /**
     * This method creates the confirm button. When the button is clicked, if
     * the authentification is successfull, the packages will be delivered,
     * it will be checked if there are packages to be withdrawn and the panel
     * will change to ViewBoxesPanel.
     * @return the button that has been created
     */

    private JButton createConfirmButton(){
        buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(loginDelMan.login(delID.getText(), password.getText())){
                        alertLabel.correctCode();
                        loginDelMan.addDeliverymanPackages();
                        loginDelMan.pickupPackages();
                        bgp.changePanel("viewBoxesPanel");
                    } else {
                        alertLabel.wrongCode();
                    }

                } catch (IOException e1) {
                    new ErrorGUIMain("Unable To Connect To Server", true);
                    return;
                }
                deleteText();
            }

        });
        return buttonConfirm;
    }

    /**
     * This method creates the back panel by inserting into it tha back button.
     * When the back button is clicked the panel will change to BoxAccessPanel
     * @return the panel that has been created
     */

    private JPanel createBackPanel(){
        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("boxAccessPanel");
            }
        });
        JPanel backpanel = new JPanel();
        backpanel.setLayout(new BorderLayout());
        backpanel.add(buttonBack, BorderLayout.WEST);
        backpanel.setOpaque(false);
        return backpanel;
    }

    /**
     * This method deletes the text from the password and id text fields
     */

    public void deleteText(){
        password.setText("");
        delID.setText("");
    }

    /**
     * This method is called when credentials are sent from the delivery man.
     * Set this as the current panel, sets the id and password text fields with
     * the strings passed as an argument and clicks the confirm button
     * @param id
     * @param password
     */

    @Override
    public void updateCredentials(String id, String password) {
        bgp.changePanel("loginDelManPanel");
        delID.setText(id);
        this.password.setText(password);
        buttonConfirm.doClick();
    }

    /**
     * This method sets the background image
     * @param g the Graphics
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/loginbackground.jpg");
        img = img.getScaledInstance(getWidth(),getHeight(), Image.SCALE_DEFAULT);
        ImageLoader imgLoader = new ImageLoader();
        imgLoader.loadImage(img, this);
        g.drawImage(img,0,0,this);
        super.paintComponent(g);
    }
}
