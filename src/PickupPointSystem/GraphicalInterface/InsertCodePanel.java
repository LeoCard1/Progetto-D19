package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class InsertCodePanel extends JPanel {
    private PickupPoint piPo;
    private ArrayList<JTextField> texts = new ArrayList<>();
    private JTextField textF = new JTextField();
    private JTextField textF2 = new JTextField();
    private int n = 0;
    JLabel l;
    JButton b;
    // l e b sono variabili necessarie per cambiare la lingua delle tab (più informazioni nella classe SetLanguage)

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public InsertCodePanel(PickupPoint pipo) {
        piPo = pipo;

        setLayout(new GridLayout(3, 1));
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        JPanel panLab = new JPanel();

        JLabel insCod = new JLabel(SetLanguage.getInstance().setInsertCodePanel()[0]);
        insCod.setFont(new Font("", Font.PLAIN, 24));
        panLab.add(insCod);
        add(panLab);
        l = insCod;

        JPanel p = new JPanel();
        p.setLayout((new GridLayout(1,2)));
        add(p);

        for(int i = 0; i<5; i++){
            JTextField text = new JTextField();
            p.add(text);
            texts.add(text);
            text.setFont(new Font("", Font.PLAIN, 24));
            text.setMaximumSize(new Dimension(200, 200));

            int indice = texts.size();
            text.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent keyEvent) {
                }

                @Override
                public synchronized void keyPressed(KeyEvent keyEvent) {
                }

                @Override
                public void keyReleased(KeyEvent keyEvent) {
                    if(keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        text.setText("");
                        texts.get(indice - 2).setText("");
                        texts.get(indice - 2).setEditable(true);
                        texts.get(indice - 2).grabFocus();
                    }
                    else {
                        texts.get(indice).grabFocus();
                        texts.get(indice - 1).setEditable(false);
                    }
                }
            });

        }










        

        JButton confBut = new JButton(SetLanguage.getInstance().setInsertCodePanel()[1]);
        add(confBut);
        b = confBut;

        ActionListener checkCode = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String code = textF.getText();
                deleteText();
                piPo.emptyBox(code);
            }
        };
        confBut.addActionListener(checkCode);
    }

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
