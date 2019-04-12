package GraphicalInterface;

import Management.PickupPoint;

import javax.swing.*;
import java.awt.*;

public class GridBoxesPanel extends JPanel {
    private PickupPoint piPo;

    public GridBoxesPanel(PickupPoint pipo) {
        piPo = pipo;

        initPanel();
    }

    private void initPanel() {
        /*
        Potrebbe essere utile creare una classe
        Format che raggruppa informazioni sulla
        disposizione delle box come il numero di
        righe per tipologia.
         */

        setLayout(new GridLayout(3, 1));
        add(makeGrid(24));
        add(makeGrid(16));
        add(makeGrid(10));

        /*
        Cosi` funziona, ma serve un modo per
        trovare il numero di tipologie di box.
        Altrimenti, questo funziona sempre, ma
        e` meno elegante (bisogna prima eliminare
        le quattro righe sopra):
         */

        /*int totalBoxes = 60;
        int rows = 4;

        setLayout(new GridLayout(rows, totalBoxes/rows));

        for (int i = 0; i < totalBoxes; i++) {
            JButton bu = new JButton("•");
            bu.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
            bu.setForeground(Color.RED);
            add(bu);
        }*/
    }

    private JPanel makeGrid(int elements) {
        int rows = 4;
        while (elements%rows != 0) {
            rows -= 1;
        }

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows, elements/rows));

        for (int i = 0; i < elements; i++) {
            JButton bu = new JButton("•");
            bu.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
            bu.setForeground(Color.RED);
            bu.setBackground(Color.ORANGE);
            grid.add(bu);
        }

        return grid;
    }
}
