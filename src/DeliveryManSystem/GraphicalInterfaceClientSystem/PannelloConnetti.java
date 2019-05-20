package DeliveryManSystem.GraphicalInterfaceClientSystem;

import DeliveryManSystem.DeliveryManClient;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;

public class PannelloConnetti extends JPanel implements Observer {


    PannelloConnetti(Frame frame , DeliveryManClient fattorino){

        GuiConnetti(frame , fattorino);
    }

    public void GuiConnetti(Frame frame , DeliveryManClient fattorino ){

        //impostazioni pannelloBackground

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setBackground(Color.black);

        //impostazioni pannelloContenitore

        JPanel pannelloContenitore = new JPanel();
        pannelloContenitore.setLayout(new GridLayout(2,1 ));

        //impostazioni pannelloDatiDiAccesso

        JPanel pannelloPasswordId = new JPanel();
        pannelloPasswordId.setBorder(BorderFactory.createEmptyBorder(40 , 10 ,10 ,10));
        JLabel labelPassword = new JLabel("Password  :  ");
        JLabel labelId = new JLabel("                Id  :  ");
        JPasswordField passwordField = new JPasswordField(15);
        JTextField idField = new JTextField(15) ;
        pannelloPasswordId.add(labelPassword);
        pannelloPasswordId.add(passwordField);
        pannelloPasswordId.add(labelId);
        pannelloPasswordId.add(idField);

        //impostazione pannelloBottoni

        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(new GridLayout(3,1));
        pannelloBottoni.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        JLabel indicazioni = new JLabel();

        //creazione bottoni

        JButton indietro = new JButton("indietro");
        JButton accedi = new JButton("Accedi");

        //impostazione bottoni

        accedi.setBackground(Color.orange);
        indietro.setBackground(Color.LIGHT_GRAY);
        accedi.setFocusable(false);
        indietro.setFocusable(false);

        //aggiunta bottoni

        pannelloBottoni.add(accedi);
        pannelloBottoni.add(indicazioni);
        pannelloBottoni.add(indietro);


        passwordField.setActionCommand("Accedi");

        idField.setActionCommand("Accedi");

        //aggiunta al pannello principale

        pannelloContenitore.add(pannelloPasswordId);
        pannelloContenitore.add(pannelloBottoni);
        add(pannelloContenitore);

        //azione bottoni

        ButtonAction indietroAction = new ButtonAction(frame , this);
        ConnettiAction accediAction = new ConnettiAction(passwordField.getPassword() , idField.getText() , fattorino , frame , this);
        indietro.addActionListener(indietroAction);
        accedi.addActionListener(accediAction);

    }

    public void update() {

    }
}
