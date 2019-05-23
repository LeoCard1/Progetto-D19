package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryManClient;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.Font.ITALIC;

/**
 * This Class create the Login Panel and everything inside it
 * @author Roberto Zappa
 * @version 1.0
 */

public class LoginPanel extends JPanel implements ActionListener , Observer {

    private Frame frame;
    private String[] languageSelector = {"English" , "Italian"};
    private JButton login;
    private DeliveryManClient deliveryman;
    private JPasswordField jpf;
    private JTextField jtf;
    private JLabel errorLabel;
    private int width;
    private int height;

    LoginPanel(Frame frame , DeliveryManClient deliveryman , int width , int height){

        this.width = width;
        this.height = height;
        this.deliveryman = deliveryman;
        this.frame = frame;
        SetLoginPanel();
    }

    /**
     * This method build the Login Panel
     */

    private void SetLoginPanel(){

        //settings panelBackground

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black,height/80 ));

        //settings panelContainer

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2,1 ));

        //settings panelPassword

        JPanel panelPasswordId = new JPanel();
        panelPasswordId.setLayout(new GridLayout(3,2));
        panelPasswordId.setBorder(BorderFactory.createTitledBorder("Sign In"));
        setLanguageSelection(panelPasswordId);
        setJTextFieldAndJPasswordField(panelPasswordId);

        //settings buttonPanel

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,height/50,height/10,height/50));

        setMessageError(buttonPanel);
        setButton(buttonPanel);

        //add to background panel

        panelContainer.add(panelPasswordId);
        panelContainer.add(buttonPanel);
        add(panelContainer);

        setAction();

    }

    /**
     * This method set the part of Panel relative of changing language
     * @param panelPasswordId
     */

    private void setLanguageSelection(JPanel panelPasswordId){

        JLabel labelLanguage = new JLabel("Language   :");
        JPanel panelLanguage = new JPanel();
        panelLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,width/7));
        panelLanguage.add(labelLanguage);

        JPanel panelSelectLanguage = new JPanel();
        panelSelectLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,0));
        JComboBox language = new JComboBox(languageSelector);
        panelSelectLanguage.add(language);

        panelPasswordId.add(panelLanguage);
        panelPasswordId.add(panelSelectLanguage);

    }

    /**
     * This method set the part of Panel relative of Password and Text Fields
     * @param panelPasswordId
     */

    private void setJTextFieldAndJPasswordField(JPanel panelPasswordId){

        JLabel labelPassword = new JLabel("Password   :");
        JLabel labelId = new JLabel("Id   :");
        jpf = new JPasswordField();
        jtf = new JTextField() ;
        JPanel panelPassword = new JPanel();
        panelPassword.setBorder(BorderFactory.createEmptyBorder(height/20,0,0,width/7));
        panelPassword.add(labelPassword );

        JPanel panelPasswordField = new JPanel();
        panelPasswordField.setLayout(new GridLayout(2,1));
        panelPasswordField.setBorder(BorderFactory.createEmptyBorder(height/20,0,0,0));
        panelPasswordField.add(jpf);

        JPanel panelText = new JPanel();
        panelText.add(labelId);

        JPanel panelTextField = new JPanel();
        panelTextField.setLayout(new GridLayout(2,1));
        panelTextField.setBorder(BorderFactory.createEmptyBorder(0,0,height/20,0));
        panelTextField.add(jtf);

        panelPasswordId.add(panelPassword);
        panelPasswordId.add(panelPasswordField);
        panelPasswordId.add(panelText);
        panelPasswordId.add(panelTextField);

    }

    /**
     * This method set the part of Panel relative of the error message visible if something go wrong with login credential
     * @param buttonPanel
     */

    private void setMessageError(JPanel buttonPanel){

        errorLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("Icons/problem.png")).getImage().getScaledInstance(width/5, height/10, Image.SCALE_DEFAULT));
        errorLabel.setIcon(imageIcon);
        Font font = new Font("Arial" ,ITALIC , 15);
        errorLabel.setBorder(BorderFactory.createTitledBorder( errorLabel.getBorder(),"Important Message!" , ITALIC , 0, font, Color.red));
        errorLabel.setVisible(false);
        buttonPanel.add(errorLabel);

    }

    /**
     * This method set the part of Panel relative of button
     * @param buttonPanel
     */

    private void setButton(JPanel buttonPanel){

        //new button

        login = new JButton("Sign In");

        //settings button

        login.setBackground(Color.orange);
        login.setFocusable(false);

        //add button

        buttonPanel.add(login);

    }

    /**
     * This method set the action of the "Sign In" button
     */

    private void setAction(){

        login.addActionListener(this);

    }

    /**
     * This method create change the panel if the credentials are right or call update method
     * @param e
     */

    public void actionPerformed(ActionEvent e) {

        try {

            String password = new String(jpf.getPassword());
            if (deliveryman.logIn(jtf.getText(),password)) {
                password = null;
                jpf = null;
                frame.remove(this);
                frame.add(new PannelloIniziale(frame));
                frame.repaint();
                frame.validate();

            } else {

                errorLabel.setText("Incorrect Password or Id");
                update();
            }

        } catch (IOException exception) {

            errorLabel.setText("Service unavailable");
            update();

        }

    }

    /**
     * This method set the error message visible
     */

    public void update() {

       errorLabel.setVisible(true);

    }

}
