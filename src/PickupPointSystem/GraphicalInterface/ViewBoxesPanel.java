package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class ViewBoxesPanel extends JPanel implements Observer {
    private PickupPoint piPo;
    private GridBoxesPanel gridBoxesPanel;

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public ViewBoxesPanel(PickupPoint pipo) {
        piPo = pipo;
        setLayout(new BorderLayout());
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        gridBoxesPanel = new GridBoxesPanel(piPo);
        add(gridBoxesPanel);
    }

    /**
     * The interface is updated after a package is added or removed.
     */

    @Override
    public void update() {
        gridBoxesPanel.update();
    }
}
