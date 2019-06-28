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

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public ViewBoxesPanel(PickupPoint pipo, BackGroundPanel bgp) {
        this.bgp = bgp;
        infoPackPanel = new InfoPackPanel(pipo);
        gridBoxesPanel = new GridBoxesPanel(pipo);

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
            }
        });
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(buttonExit, BorderLayout.WEST);
        return p;
    }

    public InfoPackPanel getInfoPackPanel(){
        return infoPackPanel;
    }

    public GridBoxesPanel getGridBoxesPanel(){
        return gridBoxesPanel;
    }



}
