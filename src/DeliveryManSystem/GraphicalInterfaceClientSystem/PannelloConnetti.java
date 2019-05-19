package DeliveryManSystem.GraphicalInterfaceClientSystem;

import javax.swing.*;
import java.awt.*;

public class PannelloConnetti extends JPanel {


    PannelloConnetti(Frame frame ){

        GuiConnetti(frame);
    }



    public void GuiConnetti(Frame frame ){

        //impostazioni pannello

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setBackground(Color.black);

        JPanel pannello = new JPanel();
        pannello.setLayout(new GridLayout(2,1 ));

        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(new GridLayout(3,1));
        pannelloBottoni.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));




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




        //creazione bottoni

        JButton indietro = new JButton("indietro");
        JButton Accedi = new JButton("Accedi");

        //impostazione bottoni

        Accedi.setBackground(Color.orange);
        indietro.setBackground(Color.LIGHT_GRAY);





        //aggiunta bottoni

        pannelloBottoni.add(Accedi );
        JLabel indicazioni = new JLabel();
        pannelloBottoni.add(indicazioni);
        pannelloBottoni.add(indietro);

        //azione bottoni

        ButtonAction indietroAction = new ButtonAction(frame , this);
        indietro.addActionListener(indietroAction);
        passwordField.setActionCommand("Accedi");

        //aggiunta al pannello principale

        pannello.add(pannelloPasswordId);
        pannello.add(pannelloBottoni);
        add(pannello);




    }
}
