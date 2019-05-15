package GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;

public class InsertCodePanel extends JPanel {
    private PickupPoint piPo;

    public InsertCodePanel(PickupPoint pipo) {
        piPo = pipo;

        setLayout(new GridLayout(3, 1));
        initPanel();
    }

    private void initPanel() {
        JPanel panLab = new JPanel();

        JLabel insCod = new JLabel("Insert code:");
        insCod.setFont(new Font("", Font.PLAIN, 24));
        panLab.add(insCod);
        add(panLab);

        JTextField textF = new JTextField();
        textF.setFont(new Font("", Font.PLAIN, 24));
        textF.setMaximumSize(new Dimension(200, 200));
        add(textF);

        add(new JButton("Confirm"));
    }
}
