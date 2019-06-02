package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.Client.DeliveryManClient;
import DeliveryManSystem.DeliveryMan;
import DeliveryManSystem.ObserverResponse;
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

public class LoginPanel extends JPanel implements ActionListener, ObserverResponse {

    private Frame frame;
    private String[] languageSelector = {SetDMLanguage.getInstance().setLoginPanel()[1], SetDMLanguage.getInstance().setLoginPanel()[2]};
    private JButton login;
    private DeliveryManClient deliveryman;
    private JPasswordField jpf;
    private JTextField jtf;
    private JLabel errorLabel;
    private int width;
    private int height;
    private int n;
    private JComboBox l;
    private JLabel l1, l2, l3, l4;
    private JPanel p;
    private JButton b;

    LoginPanel(Frame frame , DeliveryManClient deliveryman , int width , int height){

        this.width = width;
        this.height = height;
        this.deliveryman = deliveryman;
        this.frame = frame;

        deliveryman.addObserver(this);
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
        panelPasswordId.setBorder(BorderFactory.createTitledBorder(SetDMLanguage.getInstance().setLoginPanel()[3]));
        setLanguageSelection(panelPasswordId);
        setJTextFieldAndJPasswordField(panelPasswordId);
        p = panelPasswordId;

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
     * @param panelPasswordId panel that contains everything that is in the half height part of the panel
     */

    private void setLanguageSelection(JPanel panelPasswordId){

        JLabel labelLanguage = new JLabel(SetDMLanguage.getInstance().setLoginPanel()[4]);
        JPanel panelLanguage = new JPanel();
        panelLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,width/7));
        panelLanguage.add(labelLanguage);
        l1 = labelLanguage;

        JPanel panelSelectLanguage = new JPanel();
        panelSelectLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,0));
        JComboBox language = new JComboBox(languageSelector);
        panelSelectLanguage.add(language);
        l = language;

        panelPasswordId.add(panelLanguage);
        panelPasswordId.add(panelSelectLanguage);

        ActionListener languageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(language.getSelectedIndex() == n){
                    SetDMLanguage.getInstance().changeLanguage("english");
                }else{
                    SetDMLanguage.getInstance().changeLanguage("italiano");
                }
                refresh();
            }
        };

        language.addActionListener(languageListener);

    }

    /**
     * This method set the part of Panel relative of Password and Text Fields
     * @param panelPasswordId panel that contains everything that is in the half height part of the panel
     */

    private void setJTextFieldAndJPasswordField(JPanel panelPasswordId){

        JLabel labelPassword = new JLabel(SetDMLanguage.getInstance().setLoginPanel()[5]);
        JLabel labelId = new JLabel(SetDMLanguage.getInstance().setLoginPanel()[6]);
        jpf = new JPasswordField();
        jtf = new JTextField() ;
        JPanel panelPassword = new JPanel();
        panelPassword.setBorder(BorderFactory.createEmptyBorder(height/20,0,0,width/7));
        panelPassword.add(labelPassword );
        l2 = labelPassword;
        l3 = labelId;

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
     * @param buttonPanel panel that contains everything that is in the half low part of the panel
     */

    private void setMessageError(JPanel buttonPanel){

        errorLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("Icons/problem.png")).getImage().getScaledInstance(width/5, height/10, Image.SCALE_DEFAULT));
        errorLabel.setIcon(imageIcon);
        Font font = new Font("Arial" ,ITALIC , 15);
        errorLabel.setBorder(BorderFactory.createTitledBorder(errorLabel.getBorder(),SetDMLanguage.getInstance().setLoginPanel()[7] , ITALIC , 0, font, Color.red));
        errorLabel.setVisible(false);
        buttonPanel.add(errorLabel);
        l4 = errorLabel;

    }

    /**
     * This method set the part of Panel relative of button
     * @param buttonPanel panel that contains everything that is in the half low part of the panel
     */

    private void setButton(JPanel buttonPanel){

        //new button

        login = new JButton(SetDMLanguage.getInstance().setLoginPanel()[8]);

        //settings button

        login.setBackground(Color.orange);
        login.setFocusable(false);

        //add button

        buttonPanel.add(login);
        b = login;

    }

    /**
     * This method set the action of the "Sign In" button
     */

    private void setAction(){

        login.addActionListener(this);

    }

    /**
     * This method create and change the panel if the credentials are right or call update method
     * @param e The action listener
     */

    public void actionPerformed(ActionEvent e) {
        deliveryman.connectToPickupPoint(jtf.getText(), new String(jpf.getPassword()));

            /*String password = new String(jpf.getPassword());
            if (deliveryman.logIn(jtf.getText(), password)) {
                password = null;
                jpf = null;
                frame.remove(this);
                frame.add(new PannelloIniziale(frame , deliveryman , width , height));
                frame.repaint();
                frame.validate();

            } else {
                errorLabel.setText(SetDMLanguage.getInstance().setLoginPanel()[9]);
                update();
            }*/
    }

    /**
     * This method set the error message visible
     */

    public void update(String result) {

        if (result.equals("accepted")) {
            jpf = null;
            frame.remove(this);
            frame.add(new PannelloIniziale(frame , deliveryman , width , height));
            frame.repaint();
            frame.validate();
        }

        if (result.equals("refused")) {
            errorLabel.setText(SetDMLanguage.getInstance().setLoginPanel()[9]);
            errorLabel.setVisible(true);
        }

    }

    private void refresh() {
        n = Integer.parseInt(SetDMLanguage.getInstance().setLoginPanel()[0]);
        l.removeAllItems();
        l.addItem(SetDMLanguage.getInstance().setLoginPanel()[1]);
        l.addItem(SetDMLanguage.getInstance().setLoginPanel()[2]);
        p.setBorder(BorderFactory.createTitledBorder(SetDMLanguage.getInstance().setLoginPanel()[3]));
        l1.setText(SetDMLanguage.getInstance().setLoginPanel()[4]);
        l2.setText(SetDMLanguage.getInstance().setLoginPanel()[5]);
        l3.setText(SetDMLanguage.getInstance().setLoginPanel()[6]);
        l4.setBorder(BorderFactory.createTitledBorder(SetDMLanguage.getInstance().setLoginPanel()[7]));
        b.setText(SetDMLanguage.getInstance().setLoginPanel()[8]);



    }
}
