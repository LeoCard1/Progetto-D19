package GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JButton confBut = new JButton("Confirm");
        add(confBut);

        ActionListener checkCode = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String code = textF.getText();
                piPo.emptyBox(code);
            }
        };

        confBut.addActionListener(checkCode);
    }
}
