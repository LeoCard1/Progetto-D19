package GraphicalInterface;

import Management.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;

public class ViewBoxesPanel extends JPanel implements Observer {
    private PickupPoint piPo;
    private GridBoxesPanel gridBoxesPanel;

    public ViewBoxesPanel(PickupPoint pipo) {
        piPo = pipo;

        setLayout(new BorderLayout());
        initPanel();
    }

    private void initPanel() {
        gridBoxesPanel = new GridBoxesPanel(piPo);
        GridBoxesPanel grid = gridBoxesPanel;
        add(grid);
    }

    @Override
    public void update() {
        gridBoxesPanel.update();
    }
}
