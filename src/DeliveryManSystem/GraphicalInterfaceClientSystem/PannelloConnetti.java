package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryManClient;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PannelloConnetti extends JPanel implements ActionListener {
    private Frame frame;
    private String[] languageSelector = {"English" , "Italian"};
    private JButton accedi;
    private DeliveryManClient fattorino;
    private JPasswordField jpf;
    private JTextField jtf;
    private JLabel indicazioni;


    PannelloConnetti(Frame frame , DeliveryManClient fattorino){

        this.fattorino = fattorino;
        this.frame = frame;
        GuiConnetti();
    }

    public void GuiConnetti(){

        //impostazioni pannelloBackground

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setBackground(Color.black);

        //impostazioni pannelloContenitore

        JPanel pannelloContenitore = new JPanel();
        pannelloContenitore.setLayout(new GridLayout(2,1 ));

        //impostazioni pannelloDatiDiAccesso

        JPanel pannelloPasswordId = new JPanel();
        pannelloPasswordId.setLayout(new GridLayout(3,2));




        JLabel labelLanguage = new JLabel("Language   :");
        JLabel labelPassword = new JLabel("Password   :");
        JLabel labelId = new JLabel("Id   :");
        jpf = new JPasswordField(12);
        jtf = new JTextField(12) ;

        JPanel pannelloLanguage = new JPanel();
        pannelloLanguage.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        pannelloLanguage.add(labelLanguage);

        JPanel pannelloSelectLanguage = new JPanel();
        pannelloSelectLanguage.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JComboBox language = new JComboBox(languageSelector);
        pannelloSelectLanguage.add(language);



        JPanel pannelloPassword = new JPanel();
        pannelloPassword.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        pannelloPassword.add(labelPassword );

        JPanel pannelloPasswordField = new JPanel();
        pannelloPasswordField.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        pannelloPasswordField.add(jpf);

        JPanel pannelloText = new JPanel();
        pannelloText.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        pannelloText.add(labelId);

        JPanel pannelloTextField = new JPanel();
        pannelloTextField.add(jtf);


        pannelloPasswordId.add(pannelloLanguage);
        pannelloPasswordId.add(pannelloSelectLanguage);
        pannelloPasswordId.add(pannelloPassword);
        pannelloPasswordId.add(pannelloPasswordField);
        pannelloPasswordId.add(pannelloText);
        pannelloPasswordId.add(pannelloTextField);

        //impostazione pannelloBottoni

        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(new GridLayout(3,1));
        pannelloBottoni.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        JLabel indicazioni = new JLabel();

        //creazione bottoni

        JButton indietro = new JButton("indietro");
        accedi = new JButton("Login");

        //impostazione bottoni

        accedi.setBackground(Color.orange);
        indietro.setBackground(Color.LIGHT_GRAY);
        accedi.setFocusable(false);
        indietro.setFocusable(false);

        //aggiunta bottoni

        pannelloBottoni.add(indicazioni);
        pannelloBottoni.add(accedi);
        pannelloBottoni.add(indietro);

        //aggiunta al pannello principale

        pannelloContenitore.add(pannelloPasswordId);
        pannelloContenitore.add(pannelloBottoni);
        add(pannelloContenitore);

        //azione bottoni

        ButtonAction indietroAction = new ButtonAction(frame , this);
        indietro.addActionListener(indietroAction);
        setAction();

    }

    private void setAction(){
        accedi.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {



        try {

            if (fattorino.logIn(jtf.getText(),jpf.getPassword().toString())) {

                jpf = null;
                frame.remove(this);
                frame.add(new PannelloImpostazioni(frame));
                frame.repaint();
                frame.validate();

            } else {

                JOptionPane.showMessageDialog(this,"errore");
            }

        } catch (IOException eccezione) {

            System.out.println("prova tasto");

        }

    }




}
