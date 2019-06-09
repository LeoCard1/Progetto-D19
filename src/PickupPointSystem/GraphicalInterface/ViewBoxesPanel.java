package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;
import PickupPointSystem.ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class ViewBoxesPanel extends JPanel implements Observer {
    private PickupPoint piPo;
    private GridBoxesPanel gridBoxesPanel;
    private BackGroundPanel bgp;

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public ViewBoxesPanel(PickupPoint pipo, BackGroundPanel bgp) {
        this.bgp = bgp;
        piPo = pipo;
        piPo.addObserver(this);
        setLayout(new BorderLayout());
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        gridBoxesPanel = new GridBoxesPanel(piPo);
        setLayout(new BorderLayout());
        add(gridBoxesPanel, BorderLayout.CENTER);
        add(createExitPanel(), BorderLayout.NORTH);
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

    /**
     * The interface is updated after a package is added or removed.
     */

    @Override
    public void update() {
        bgp.changePanel("viewBoxesPanel");
        gridBoxesPanel.checkState();
    }
}
