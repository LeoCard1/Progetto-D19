package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import PickupPointSystem.GraphicalInterface.LoadingGUI.LoadingGUIMain;
import PickupPointSystem.ObserverPattern.Observer;
import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class InsertCodePanel extends JPanel {
    private PickupPoint piPo;
    private BackGroundPanel bgp;
    private JTextField textF = new JTextField();
    private AlertLabel alertLabel;
    JLabel l;
    JButton b;
    // l e b sono variabili necessarie per cambiare la lingua delle tab (più informazioni nella classe SetLanguage)

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public InsertCodePanel(PickupPoint pipo, BackGroundPanel bgp) {
        this.bgp = bgp;
        piPo = pipo;
        setLayout(new GridLayout(4, 1));
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        alertLabel = new AlertLabel("Correct Code", "Wrong Code");
        textF.setFont(new Font("", Font.PLAIN, 24));
        add(createInsCodLabel());
        add(textF);
        add(createConfirmButton());
        add(alertLabel);
    }

    private JLabel createInsCodLabel(){
        JLabel insCod = new JLabel(SetLanguage.getInstance().setInsertCodePanel()[0]);
        insCod.setHorizontalAlignment(JLabel.CENTER);
        insCod.setFont(new Font("", Font.PLAIN, 24));
        l = insCod;
        return insCod;
    }

    private JButton createConfirmButton(){
        JButton confBut = new JButton(SetLanguage.getInstance().setInsertCodePanel()[1]);

        b = confBut;
        confBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = textF.getText();
                deleteText();
                try {
                    if(!piPo.emptyBox(code)){
                        alertLabel.wrongCode();
                    } else {
                        alertLabel.correctCode();
                        bgp.changePanel("viewBoxesPanel");
                    }
                } catch (IOException e1) {
                    new ErrorGUIMain("Unable To Connect To Server",true);
                    return;
                }
            }
        });
        return confBut;
    }

    /*
    private JPanel createPasswordField() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout((new GridLayout(1, 8)));
        for(int i = 0; i<8; i++){
            texts.add(new JTextField());
        }
        for (JTextField text : texts) {
            passwordPanel.add(text);
            text.setFont(new Font("", Font.PLAIN, 24));
            int index = texts.indexOf(text);
            text.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent keyEvent) {
                }
                @Override
                public void keyPressed(KeyEvent keyEvent) {
                }
                @Override
                public void keyReleased(KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE && index!=0) {
                        text.setText("");
                        texts.get(index-1).setEditable(true);
                        texts.get(index-1).grabFocus();
                        return;
                    }
                    if(index!=texts.size()-1) {
                        texts.get(index + 1).grabFocus();
                        text.setEditable(false);
                    }

                }
            });
        }
        return passwordPanel;
    }
    */

    /**
     * This method deletes the text from the JTextField textF.
     */

    public void deleteText(){
        textF.setText("");
    }

    /**
     * The interface is updated after a package is added or removed.
     */

    public void refresh() {
        l.setText(SetLanguage.getInstance().setInsertCodePanel()[0]);
        b.setText(SetLanguage.getInstance().setInsertCodePanel()[1]);
    }

}
