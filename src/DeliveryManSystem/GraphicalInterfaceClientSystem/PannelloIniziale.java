package DeliveryManSystem.GraphicalInterfaceClientSystem;



import DeliveryManSystem.DeliveryManClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.awt.Font.ITALIC;

public class PannelloIniziale extends JPanel {

    private Frame frame;
    private JButton sendList;
    private DeliveryManClient deliveryman;
    private JLabel errorLabel;
    private int width;
    private int height;

    PannelloIniziale(Frame frame , DeliveryManClient deliveryman , int width , int height){

        updateList(deliveryman);
        this.width = width;
        this.height = height;
        this.deliveryman = deliveryman;
        this.frame = frame;
        SetListPanel();

    }

    public void updateList( DeliveryManClient deliveryMan){

        try {
            deliveryMan.updateList();
        }catch (IOException exception){



        }

    }

    private void SetListPanel(){

        //settings panelBackground

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black,height/80 ));

        //settings panelContainer

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2,1 ));

        //settings panelList

        JPanel panelList = new JPanel();

        //settings buttonPanel

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,height/50,height/10,height/50));

        setMessageError(buttonPanel);
        setButton(buttonPanel);

        //add to background panel

        panelContainer.add(panelList);
        panelContainer.add(buttonPanel);
        add(panelContainer);

        //da implementare:

        //setAction();

    }

    private void setMessageError(JPanel buttonPanel){

        errorLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("Icons/problem.png")).getImage().getScaledInstance(width/5, height/10, Image.SCALE_DEFAULT));
        errorLabel.setIcon(imageIcon);
        Font font = new Font("Arial" ,ITALIC , 15);
        errorLabel.setBorder(BorderFactory.createTitledBorder( errorLabel.getBorder(),"Important Message!" , ITALIC , 0, font, Color.red));
        errorLabel.setVisible(false);
        buttonPanel.add(errorLabel);

    }

    private void setButton(JPanel buttonPanel){

        //new button

        sendList = new JButton("Send List");

        //settings button

        sendList.setBackground(Color.orange);
        sendList.setFocusable(false);

        //add button

        buttonPanel.add(sendList);

    }

   /* private void setAction(){

        sendList.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        try {

            String password = new String(jpf.getPassword());
            if (deliveryman.logIn(jtf.getText(),password)) {
                password = null;
                jpf = null;
                frame.remove(this);
                frame.add(new PannelloIniziale(frame , deliveryman , width , height));
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

    }*/

    public void update() {

        errorLabel.setVisible(true);

    }



}
