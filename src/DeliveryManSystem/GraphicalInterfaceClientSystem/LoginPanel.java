package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryMan;
import DeliveryManSystem.Exceptions.UsernameOrPasswordException;
import DeliveryManSystem.Observers.ObserverLogin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Font.ITALIC;

/**
 * This Class creates the Login Panel and everything inside it
 * @author Zappa Roberto
 * @version 1.0.2
 */

public class LoginPanel extends JPanel {

    private DeliveryMan deliveryMan;
    private ArrayList<ObserverLogin> observers = new ArrayList<>();
    private String[] languageSelector = {SetDMLanguage.getInstance().setLoginPanel()[1], SetDMLanguage.getInstance().setLoginPanel()[2]};
    private JButton login;
    private JPasswordField jpf;
    private JTextField jtf;
    private AlertLabel errorLabel;
    private BackgroundPanel bgp;
    private int width;
    private int height;
    private int n;
    private JComboBox l;
    private JLabel l1, l2, l3;
    private AlertLabel l4;
    private JPanel p;
    private JButton b;

    /**
     * The constructor
     * @param bgp
     * @param width
     * @param height
     */

    LoginPanel(BackgroundPanel bgp, int width, int height){
        this.width = width;
        this.height = height;
        this.bgp = bgp;
        initLoginPanel();
    }

    /**
     * This class initializes the LoginPanel
     */

    private void initLoginPanel(){
        setOpaque(false);
        setLayout(new GridLayout(2,1 ));

        add(panelPasswordId());
        add(buttonPanel());

        login.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    deliveryMan = new DeliveryMan(jtf.getText(), new String(jpf.getPassword()));
                    notifyObservers(deliveryMan);
                    bgp.changePanel("startingPanel");
                } catch (UsernameOrPasswordException u) {
                    errorLabel.showMessageForAFewSeconds(SetDMLanguage.getInstance().setLoginPanel()[9], true);
                } catch (IOException e1){
                    errorLabel.showMessageForAFewSeconds(SetDMLanguage.getInstance().setLoginPanel()[10], true);
                }
            }
        });
    }

    /**
     * This method creates the confirmation button
     * and related details
     * @return The panel containing the button
     */

    private JPanel buttonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,height/50,height/10,height/50));

        setMessageError(buttonPanel);
        setButton(buttonPanel);
        return buttonPanel;
    }

    /**
     * This method creates the panel used for
     * the deliveryman's login operations.
     * @return The panel containing fields for a deliveryman's ID and password
     */

    private JPanel panelPasswordId(){
        JPanel panelPasswordId = new JPanel();
        panelPasswordId.setOpaque(false);
        panelPasswordId.setLayout(new GridLayout(3,2));
        TitledBorder border = BorderFactory.createTitledBorder(SetDMLanguage.getInstance().setLoginPanel()[3]);
        border.setTitleFont(new Font("", Font.BOLD, height/25));
        border.setTitleColor(Color.WHITE);
        panelPasswordId.setBorder(border);

        setLanguageSelection(panelPasswordId);
        setJTextFieldAndJPasswordField(panelPasswordId);

        p = panelPasswordId;
        return panelPasswordId;
    }

    /**
     * This method is called whenever the language is changed
     * and builds the panel's language-dependent elements
     * @param panelPasswordId The panel containing fields for a deliveryman's ID and password
     */

    private void setLanguageSelection(JPanel panelPasswordId){
        JLabel labelLanguage = new JLabel(SetDMLanguage.getInstance().setLoginPanel()[4]);
        labelLanguage.setForeground(Color.WHITE);
        labelLanguage.setFont(new Font("", Font.BOLD, height/34));

        JPanel panelLanguage = new JPanel();
        panelLanguage.setOpaque(false);
        panelLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,width/7));
        panelLanguage.add(labelLanguage);
        l1 = labelLanguage;

        JPanel panelSelectLanguage = new JPanel();
        panelSelectLanguage.setOpaque(false);
        panelSelectLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,0));
        JComboBox language = new JComboBox(languageSelector);
        language.setFont(new Font("", Font.BOLD, height/34));
        panelSelectLanguage.add(language);
        l = language;

        panelPasswordId.add(panelLanguage);
        panelPasswordId.add(panelSelectLanguage);

        ActionListener languageListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (language.getSelectedIndex() == n) {
                    SetDMLanguage.getInstance().changeLanguage("english");
                } else {
                    SetDMLanguage.getInstance().changeLanguage("italiano");
                }
                refresh();
            }
        };

        language.addActionListener(languageListener);

    }

    /**
     * This method builds the panel containing the fields
     * used by a deliveryman to log in the system
     * @param panelPasswordId The panel containing fields for a deliveryman's ID and password
     */

    private void setJTextFieldAndJPasswordField(JPanel panelPasswordId){
        JLabel labelPassword = new JLabel(SetDMLanguage.getInstance().setLoginPanel()[5]);
        labelPassword.setForeground(Color.WHITE);
        JLabel labelId = new JLabel(SetDMLanguage.getInstance().setLoginPanel()[6]);
        labelId.setForeground(Color.WHITE);
        jpf = new JPasswordField();
        jpf.setFont(new Font("", Font.PLAIN, height/34));
        labelPassword.setFont(new Font("", Font.BOLD, height/34));
        labelId.setFont(new Font("", Font.BOLD, height/34));
        jtf = new JTextField() ;
        jtf.setFont(new Font("", Font.PLAIN, height/34));

        JPanel panelPassword = new JPanel();
        panelPassword.setOpaque(false);
        panelPassword.add(labelPassword);
        l2 = labelPassword;
        l3 = labelId;

        JPanel panelPasswordField = new JPanel();
        panelPasswordField.setOpaque(false);
        panelPasswordField.setLayout(new GridLayout(2,1));
        panelPasswordField.setBorder(BorderFactory.createEmptyBorder(0,0,height/20,0));
        panelPasswordField.add(jpf);

        JPanel panelText = new JPanel();
        panelText.setOpaque(false);
        panelText.setBorder(BorderFactory.createEmptyBorder(height/20,0,0,width/7));
        panelText.add(labelId);

        JPanel panelTextField = new JPanel();
        panelTextField.setOpaque(false);
        panelTextField.setLayout(new GridLayout(2,1));
        panelTextField.setBorder(BorderFactory.createEmptyBorder(height/20,0,0,0));
        panelTextField.add(jtf);

        panelPasswordId.add(panelText);
        panelPasswordId.add(panelTextField);
        panelPasswordId.add(panelPassword);
        panelPasswordId.add(panelPasswordField);
    }

    /**
     * This method builds the section that is made visible
     * whenever the login operation causes a problem, showing an error message
     * @param buttonPanel The panel containing the confirmation button
     */

    private void setMessageError(JPanel buttonPanel){
        errorLabel = new AlertLabel();
        errorLabel.setForeground(Color.WHITE);
        errorLabel.setTextAndIcon(SetDMLanguage.getInstance().setLoginPanel()[11], true);
        errorLabel.setDefaultTextAndIcon(SetDMLanguage.getInstance().setLoginPanel()[11], true);
        buttonPanel.add(errorLabel);
        l4 = errorLabel;
    }

    /**
     *  This method builds the panel containing the
     *  confirmation button
     * @param buttonPanel The panel containing the confirmation button
     */

    private void setButton(JPanel buttonPanel){
        //new button

        login = new JButton(SetDMLanguage.getInstance().setLoginPanel()[8]);
        login.setFont(new Font("", Font.BOLD, height/30));

        //settings button

        login.setBackground(new Color(255,153,0));
        login.setFocusable(false);

        //add button

        buttonPanel.add(login);
        b = login;
    }

    /**
     * This method refreshes the panel after
     * the language has been changed
     */

    private void refresh() {
        n = Integer.parseInt(SetDMLanguage.getInstance().setLoginPanel()[0]);
        l.removeAllItems();

        l.addItem(SetDMLanguage.getInstance().setLoginPanel()[1]);
        l.addItem(SetDMLanguage.getInstance().setLoginPanel()[2]);
        TitledBorder border = BorderFactory.createTitledBorder(SetDMLanguage.getInstance().setLoginPanel()[3]);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("", Font.BOLD, height/25));
        p.setBorder(border);
        l1.setText(SetDMLanguage.getInstance().setLoginPanel()[4]);
        l2.setText(SetDMLanguage.getInstance().setLoginPanel()[5]);
        l3.setText(SetDMLanguage.getInstance().setLoginPanel()[6]);
        TitledBorder borderl4 = BorderFactory.createTitledBorder(SetDMLanguage.getInstance().setLoginPanel()[7]);
        borderl4.setTitleFont(new Font("Arial" ,ITALIC , height/25));
        borderl4.setTitleColor(Color.RED);
        l4.setBorder(borderl4);
        l4.setTextAndIcon(SetDMLanguage.getInstance().setLoginPanel()[11], true);
        l4.setDefaultTextAndIcon(SetDMLanguage.getInstance().setLoginPanel()[11], true);
        b.setText(SetDMLanguage.getInstance().setLoginPanel()[8]);
    }

    /**
     * This method delete credentials for better security
     */

    public void deleteText(){
        jpf.setText("");
        jtf.setText("");
    }



    public void addObserver(ObserverLogin ob){
        observers.add(ob);
    }

    public void notifyObservers(DeliveryMan deliveryMan){
        for(ObserverLogin ob : observers){
            ob.update(deliveryMan);
        }
    }
}
