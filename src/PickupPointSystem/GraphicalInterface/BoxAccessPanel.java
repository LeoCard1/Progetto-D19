package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class BoxAccessPanel extends JPanel {
    private PickupPoint piPo;
    private GraIntMain gra;
    private InsertCodePanel ins;
    // c, l ed n sono variabili necessarie per cambiare la lingua delle tab (più informazioni nella classe SetLanguage)
    private JComboBox c;
    private JLabel l;
    private int n;

    /**
     * This is the constructor.
     * @param pipo The pickup point.
     * @param gra The GUI itself.
     */

    public BoxAccessPanel(PickupPoint pipo, GraIntMain gra) {
        piPo = pipo;
        this.gra = gra;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        add(languagePanel());
        ins = new InsertCodePanel(piPo);
        add(ins);
    }

    /**
     * The 'language' part of the panel.
     * @return The panel that has been created.
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
     * The refresh method is needed after changing the language.
     */

    private void refresh() {
        n = Integer.parseInt(SetLanguage.getInstance().setBoxAccessPanel()[0]);
        l.setText(SetLanguage.getInstance().setBoxAccessPanel()[1]);
        c.removeAllItems();
        c.addItem(SetLanguage.getInstance().setBoxAccessPanel()[2]);
        c.addItem(SetLanguage.getInstance().setBoxAccessPanel()[3]);
    }

}
