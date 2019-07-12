package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class InsertCodePanel extends JPanel {
    private PickupPoint piPo;
    private BackGroundPanel bgp;
    private JTextField textF = new JTextField();
    private AlertLabel alertLabel;
    private int height;
    private int width;
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
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        alertLabel = new AlertLabel("Correct Code", "Wrong Code");
        textF.setFont(new Font("", Font.PLAIN, height/20));
        setLayout(new GridLayout(4, 1));
        setPreferredSize(new Dimension(width/4,height/3));
        add(createInsCodLabel());
        add(textF);
        add(createConfirmButton());
        add(alertLabel);
    }

    private JLabel createInsCodLabel(){
        JLabel insCod = new JLabel(SetLanguage.getInstance().setInsertCodePanel()[0]);
        insCod.setHorizontalAlignment(JLabel.CENTER);
        insCod.setFont(new Font("", Font.PLAIN, height/20));

        l = insCod;
        return insCod;
    }

    private JPanel createConfirmButton(){
        JButton confBut = new JButton(SetLanguage.getInstance().setInsertCodePanel()[1]);
        confBut.setPreferredSize(new Dimension(width/10, height/20));
        confBut.setFont(new Font("", Font.PLAIN, height/50));
        confBut.setBackground(Color.WHITE);

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
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, height/30));
        p.setOpaque(false);
        p.add(confBut);
        return p;
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
