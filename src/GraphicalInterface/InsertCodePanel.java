package GraphicalInterface;

import Management.PickupPoint;

import javax.swing.*;
import java.awt.*;

public class InsertCodePanel extends JPanel {
    private PickupPoint piPo;

    public InsertCodePanel(PickupPoint pipo) {
        piPo = pipo;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initPanel();
    }

    private void initPanel() {
        /*
        Per qualche motivo questi elementi vengono
        sfasati, devo capire perché.
         */

        add(new JLabel("Insert code:"));

        JTextField textF = new JTextField();
        textF.setMaximumSize(new Dimension(200, 200));
        add(textF);

        add(new JButton("Confirm"));
    }
}
