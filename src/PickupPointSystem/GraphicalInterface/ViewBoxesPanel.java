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
    private PickupPoint piPo;
    private GridBoxesPanel gridBoxesPanel;
    private InfoPackPanel infoPackPanel;
    private BackGroundPanel bgp;

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public ViewBoxesPanel(PickupPoint pipo, BackGroundPanel bgp) {
        this.bgp = bgp;
        piPo = pipo;
        infoPackPanel = new InfoPackPanel(piPo);
        gridBoxesPanel = new GridBoxesPanel(piPo);
        bgp.addObserver(infoPackPanel);
        bgp.addObserver(gridBoxesPanel);


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
        Toolkit tk = getDefaultToolkit();
        int height = tk.getScreenSize().height;
        int width = tk.getScreenSize().width;
        JPanel centeredPanel = new JPanel();
        centeredPanel.setLayout(new GridLayout(1,2));
        centeredPanel.add(infoPackPanel);
        centeredPanel.add(gridBoxesPanel);
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
        JButton buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("startPanel");
                gridBoxesPanel.closeBoxes();
            }
        });
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(buttonExit, BorderLayout.WEST);
        return p;
    }



}
