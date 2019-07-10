package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;
import PickupPointSystem.ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;


/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class ViewBoxesPanel extends JPanel {
    private GridBoxesPanel gridBoxesPanel;
    private InfoPackPanel infoPackPanel;
    private BackGroundPanel bgp;
    private int height;
    private int width;

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public ViewBoxesPanel(PickupPoint pipo, BackGroundPanel bgp) {
        this.bgp = bgp;
        infoPackPanel = new InfoPackPanel(pipo);
        gridBoxesPanel = new GridBoxesPanel(pipo);
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;

        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        setLayout(new BorderLayout());
        add(createExitPanel(), BorderLayout.NORTH);
        add(createCenteredPanel(), BorderLayout.CENTER);

    }

    private JPanel createCenteredPanel(){
        JPanel centeredPanel = new JPanel();
        infoPackPanel.setOpaque(false);
        centeredPanel.setLayout(new GridLayout(1,2));
        centeredPanel.add(infoPackPanel);
        centeredPanel.add(gridBoxesPanel);
        centeredPanel.setOpaque(false);
        return centeredPanel;
    }

    /**
     * This method creates the ExitPanel by inserting the buttonExit into it,
     * when the button is clicked the panel is changed to startPanel and the
     * box icons of the gridBoxesPanel are removed.
     *
     * @return JPanel
     */

    private JPanel createExitPanel(){
        BackButton buttonBack = new BackButton();
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("startPanel");
            }
        });
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(buttonBack, BorderLayout.WEST);
        p.setOpaque(false);
        return p;
    }

    public InfoPackPanel getInfoPackPanel(){
        return infoPackPanel;
    }

    public GridBoxesPanel getGridBoxesPanel(){
        return gridBoxesPanel;
    }

    /**
     * This method sets the background image
     * @param g the Graphics
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/lightbluebackground.jpg");
        img = img.getScaledInstance(getWidth(),getHeight(), Image.SCALE_DEFAULT);
        ImageLoader imgLoader = new ImageLoader();
        imgLoader.loadImage(img, this);
        g.drawImage(img,0,0,this);
        super.paintComponent(g);
    }



}
