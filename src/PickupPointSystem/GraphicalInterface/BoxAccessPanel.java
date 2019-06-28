package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * This class is the access panel for the parcel collection
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class BoxAccessPanel extends JPanel {
    private PickupPoint piPo;
    private GraIntMain gra;
    private BackGroundPanel bgp;
    private InsertCodePanel ins;
    // c, l ed n sono variabili necessarie per cambiare la lingua delle tab (più informazioni nella classe SetLanguage)
    private JComboBox c;
    private JLabel l;
    private int n;

    /**
     * The constructor.
     * @param pipo the Pickup Point
     * @param gra the frame
     * @param bgp the BackGroundPanel
     */

    public BoxAccessPanel(PickupPoint pipo, GraIntMain gra, BackGroundPanel bgp) {
        piPo = pipo;
        this.gra = gra;
        this.bgp = bgp;

        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        setLayout(new BorderLayout());
        add(createPasswordPanel(), BorderLayout.CENTER);
        add(createNorthPanel(), BorderLayout.NORTH);
    }

    /**
     * This method creates the PasswordPanel by inserting the InsertCodePanel and the
     * languagePanel into it
     * @return the panel that has been created
     */

    private JPanel createPasswordPanel(){
        ins = new InsertCodePanel(piPo, bgp);
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
        p1.add(languagePanel());
        p1.add(ins);
        Toolkit tk = getDefaultToolkit();
        int height = tk.getScreenSize().height;
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/8));
        p2.add(p1);
        return p2;
    }

    /**
     * This method creates the NorthPanel by inserting the buttonExit and the
     * buttonDelManLogin into it.
     * When the buttonExit is clicked, the panel is changed to startPanel.
     * When the buttonDelManLogin, the panel is changed to loginDelManPanel.
     * @return the panel that has been created
     */

    private JPanel createNorthPanel(){
        JButton buttonExit = new JButton("Exit");
        JButton buttonDelManLogin = new JButton("I'm delivery man");
        buttonDelManLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("loginDelManPanel");
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("startPanel");
            }
        });
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(buttonExit, BorderLayout.WEST);
        p.add(buttonDelManLogin,BorderLayout.EAST);
        return p;
    }

    /**
     * The 'language' part of the panel.
     * @return the panel that has been created.
     */

    private JPanel languagePanel() {
        JPanel langPan = new JPanel();
        langPan.setLayout(new BoxLayout(langPan, BoxLayout.PAGE_AXIS));

        JPanel panBox = new JPanel();

        JComboBox langBox = new JComboBox();
        langBox.addItem(SetLanguage.getInstance().setBoxAccessPanel()[2]);
        langBox.addItem(SetLanguage.getInstance().setBoxAccessPanel()[3]);
        c = langBox;

        JLabel labelBox = new JLabel(SetLanguage.getInstance().setBoxAccessPanel()[1]);
        panBox.add(labelBox);
        panBox.add(langBox);
        langPan.add(panBox);
        l = labelBox;

        // qui bisogna inserire i comandi quando scegli la lingua dal menù
        ActionListener languageListenner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(langBox.getSelectedIndex() == n){
                    SetLanguage.getInstance().changeLanguage("english");
                }else{
                    SetLanguage.getInstance().changeLanguage("italiano");
                }
                refresh();
                gra.refresh();
                ins.refresh();
            }
        };

        langBox.addActionListener(languageListenner);

        return langPan;
    }

    /**
     * @return the InsertCodePanel
     */

    public InsertCodePanel getInsertCodePanel(){
        return ins;
    }

    /**
     * The refresh method is needed after changing the language.
     */

    public void refresh() {
        n = Integer.parseInt(SetLanguage.getInstance().setBoxAccessPanel()[0]);
        l.setText(SetLanguage.getInstance().setBoxAccessPanel()[1]);
        c.removeAllItems();
        c.addItem(SetLanguage.getInstance().setBoxAccessPanel()[2]);
        c.addItem(SetLanguage.getInstance().setBoxAccessPanel()[3]);
    }

}
