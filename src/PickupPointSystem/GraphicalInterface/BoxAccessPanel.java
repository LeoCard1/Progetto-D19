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
    private int height;

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
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;

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
     * This method creates the PasswordPanel by inserting the InsertCodePanel into it
     * @return the panel that has been created
     */

    private JPanel createPasswordPanel(){
        ins = new InsertCodePanel(piPo, bgp);
        ins.setOpaque(false);
        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setOpaque(false);
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/8));
        p.add(ins);
        return p;
    }

    /**
     * This method creates the NorthPanel by inserting the buttonExit, the languagePanel
     * and the buttonDelManLogin into it.
     * When the buttonExit is clicked, the panel is changed to startPanel.
     * When the buttonDelManLogin, the panel is changed to loginDelManPanel.
     * @return the panel that has been created
     */

    private JPanel createNorthPanel(){
        BackButton buttonBack = new BackButton();
        JButton buttonDelManLogin = new JButton();
        Image delImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/deliveryman.jpg").getScaledInstance(height/20, height/20,Image.SCALE_DEFAULT);
        buttonDelManLogin.setIcon(new ImageIcon(delImage));
        buttonDelManLogin.setContentAreaFilled(false);
        buttonDelManLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("loginDelManPanel");
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("startPanel");
            }
        });
        JPanel langPan = new JPanel();
        langPan.setLayout(new BorderLayout());
        langPan.add(languagePanel(), BorderLayout.SOUTH);
        langPan.setOpaque(false);
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.add(buttonBack, BorderLayout.WEST);
        northPanel.add(langPan, BorderLayout.CENTER);
        northPanel.add(buttonDelManLogin,BorderLayout.EAST);
        northPanel.setOpaque(false);
        return northPanel;
    }

    /**
     * The 'language' part of the panel.
     * @return the panel that has been created.
     */

    private JPanel languagePanel() {
        JPanel langPan = new JPanel();
        langPan.setLayout(new BoxLayout(langPan, BoxLayout.PAGE_AXIS));
        JPanel panBox = new JPanel();
        panBox.setOpaque(false);
        JComboBox langBox = new JComboBox();
        langBox.addItem(SetLanguage.getInstance().setBoxAccessPanel()[2]);
        langBox.addItem(SetLanguage.getInstance().setBoxAccessPanel()[3]);
        c = langBox;
        JLabel labelBox = new JLabel(SetLanguage.getInstance().setBoxAccessPanel()[1]);
        Image britishFlagImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/britishflag.jpg").getScaledInstance(height/20, height/30,Image.SCALE_DEFAULT);
        Image italianFlagImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/italianflag.jpg").getScaledInstance(height/20, height/30,Image.SCALE_DEFAULT);
        labelBox.setIcon(new ImageIcon(britishFlagImage));
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
                    labelBox.setIcon(new ImageIcon(britishFlagImage));
                }else{
                    SetLanguage.getInstance().changeLanguage("italiano");
                    labelBox.setIcon(new ImageIcon(italianFlagImage));
                }
                refresh();
                gra.refresh();
                ins.refresh();
            }
        };

        langBox.addActionListener(languageListenner);
        langPan.setOpaque(false);

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

    /**
     * This method sets the background image
     * @param g the Graphics
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/loginbackground.jpg");
        img = img.getScaledInstance(getWidth(),getHeight(), Image.SCALE_DEFAULT);
        ImageLoader imgLoader = new ImageLoader();
        imgLoader.loadImage(img, this);
        g.drawImage(img,0,0,this);
        super.paintComponent(g);
    }

}
