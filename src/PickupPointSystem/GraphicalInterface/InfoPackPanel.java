package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Toolkit.getDefaultToolkit;

public class InfoPackPanel extends JPanel {

    private int width;
    private int height;
    private PickupPoint piPo;
    private int totalBoxes;
    private StringBuilder text;
    private ArrayList<Boolean> available = new ArrayList<>();

    public InfoPackPanel(PickupPoint piPo) {
        this.piPo = piPo;
        totalBoxes = piPo.getSmallBoxes() + piPo.getMediumBoxes() + piPo.getLargeBoxes();
        text = new StringBuilder();

        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;
        setPreferredSize(new Dimension(width*2/6, height*2/3));


        initPanel();
    }

    private void initPanel() {
        available.clear();
        for (int i = 0; i < totalBoxes; i++) {
            if (piPo.getBoxFromIndex(i).isAvailable()) available.add(true);
            else available.add(false);
        }

        removeAll();
        JLabel textLabel = new JLabel(text.toString());

        add(textLabel);

        text = new StringBuilder();
    }

    public void refresh() {
        text.append("<html><font face = 'Times New Roman' size = 5 color = '#696969'>");
        for (int i = 0; i < available.size(); i++) {
            if (available.get(i) && !piPo.getBoxFromIndex(i).isAvailable()) {
                text.append("Box " + (i+1) + " opened: deposit package (" + piPo.getBoxFromIndex(i).getPack().getId() + ")<br/>");
            }

            if (!available.get(i) && piPo.getBoxFromIndex(i).isAvailable()) {
                text.append("Box " + (i+1) + " opened: pickup package<br/>");
            }
        }

        initPanel();
    }

}
