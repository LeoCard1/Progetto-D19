package GraphicalInterface;

import Management.PickupPoint;

import javax.swing.*;
import java.awt.*;

public class ViewBoxesPanel extends JPanel {
    private PickupPoint piPo;

    public ViewBoxesPanel(PickupPoint pipo) {
        piPo = pipo;

        setLayout(new BorderLayout());
        initPanel();
    }

    private void initPanel() {
        GridBoxesPanel grid = new GridBoxesPanel(piPo);
        add(grid);
    }
}
