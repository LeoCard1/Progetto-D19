package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryManClient;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.Font.ITALIC;

public class LoginPanel extends JPanel implements ActionListener , Observer {

    private Frame frame;
    private String[] languageSelector = {"English" , "Italian"};
    private JButton login;
    private DeliveryManClient deliveryman;
    private JPasswordField jpf;
    private JTextField jtf;
    private JLabel indicazioni;
    private int width;
    private int height;

    LoginPanel(Frame frame , DeliveryManClient deliveryman , int width , int height){

        this.width = width;
        this.height = height;
        this.deliveryman = deliveryman;
        this.frame = frame;
        SetLoginPanel();
    }

    public void SetLoginPanel(){

        //impostazioni pannelloBackground

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black,height/80 ));

        //impostazioni pannelloContenitore

        JPanel pannelloContenitore = new JPanel();
        pannelloContenitore.setLayout(new GridLayout(2,1 ));

        //impostazioni pannelloDatiDiAccesso

        JPanel pannelloPasswordId = new JPanel();
        pannelloPasswordId.setLayout(new GridLayout(3,2));
        pannelloPasswordId.setBorder(BorderFactory.createTitledBorder("Sign In"));
        setLanguageSelection(pannelloPasswordId);
        setJTextFieldAndJPasswordField(pannelloPasswordId);

        //impostazione pannelloBottoni

        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(new GridLayout(2,1));
        pannelloBottoni.setBorder(BorderFactory.createEmptyBorder(0,height/50,height/10,height/50));


        setMessageError(pannelloBottoni);
        setBotton(pannelloBottoni);

        //aggiunta al pannello principale

        pannelloContenitore.add(pannelloPasswordId);
        pannelloContenitore.add(pannelloBottoni);
        add(pannelloContenitore);

        setAction();

    }

    private void setLanguageSelection(JPanel pannelloPasswordId){

        JLabel labelLanguage = new JLabel("Language   :");
        JPanel pannelloLanguage = new JPanel();
        pannelloLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,width/7));
        pannelloLanguage.add(labelLanguage);

        JPanel pannelloSelectLanguage = new JPanel();
        pannelloSelectLanguage.setBorder(BorderFactory.createEmptyBorder(height/15,0,0,0));
        JComboBox language = new JComboBox(languageSelector);
        pannelloSelectLanguage.add(language);

        pannelloPasswordId.add(pannelloLanguage);
        pannelloPasswordId.add(pannelloSelectLanguage);

    }

    private void setJTextFieldAndJPasswordField(JPanel pannelloPasswordId){

        JLabel labelPassword = new JLabel("Password   :");
        JLabel labelId = new JLabel("Id   :");
        jpf = new JPasswordField();
        jtf = new JTextField() ;
        JPanel pannelloPassword = new JPanel();
        pannelloPassword.setBorder(BorderFactory.createEmptyBorder(height/20,0,0,width/7));
        pannelloPassword.add(labelPassword );

        JPanel pannelloPasswordField = new JPanel();
        pannelloPasswordField.setLayout(new GridLayout(2,1));
        pannelloPasswordField.setBorder(BorderFactory.createEmptyBorder(height/20,0,0,0));
        pannelloPasswordField.add(jpf);

        JPanel pannelloText = new JPanel();
        pannelloText.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        pannelloText.add(labelId);

        JPanel pannelloTextField = new JPanel();
        pannelloTextField.setLayout(new GridLayout(2,1));
        pannelloTextField.setBorder(BorderFactory.createEmptyBorder(0,0,height/20,0));
        pannelloTextField.add(jtf);

        pannelloPasswordId.add(pannelloPassword);
        pannelloPasswordId.add(pannelloPasswordField);
        pannelloPasswordId.add(pannelloText);
        pannelloPasswordId.add(pannelloTextField);

    }

    private void setMessageError(JPanel pannelloBottoni){

        indicazioni = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("Icons/problem.png")).getImage().getScaledInstance(width/5, height/10, Image.SCALE_DEFAULT));
        indicazioni.setIcon(imageIcon);
        Font font = new Font("Arial" ,ITALIC , 15);
        indicazioni.setBorder(BorderFactory.createTitledBorder( indicazioni.getBorder(),"Important Message!" , ITALIC , 0, font, Color.red));
        indicazioni.setVisible(false);
        pannelloBottoni.add(indicazioni);

    }

    private void setBotton(JPanel pannelloBottoni){

        //creazione bottoni

        login = new JButton("Sign In");

        //impostazione bottoni

        login.setBackground(Color.orange);
        login.setFocusable(false);

        //aggiunta bottoni

        pannelloBottoni.add(login);

    }

    private void setAction(){

        login.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        try {

            String password = new String(jpf.getPassword());
            if (deliveryman.logIn(jtf.getText(),password)) {
                password = "";
                jpf = null;
                frame.remove(this);
                frame.add(new PannelloImpostazioni(frame));
                frame.repaint();
                frame.validate();

            } else {

                indicazioni.setText("Incorrect Password or Id");
                update();
            }

        } catch (IOException eccezione) {

            indicazioni.setText("Service unavailable");
            update();

        }

    }

    public void update() {

       indicazioni.setVisible(true);

    }

}
