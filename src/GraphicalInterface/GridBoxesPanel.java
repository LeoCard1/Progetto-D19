package GraphicalInterface;

import LockerSystem.BoxType.Box;
import Management.PickupPoint;

import javax.swing.*;
import java.awt.*;

public class GridBoxesPanel extends JPanel {
    private PickupPoint piPo;
    private static int boxCounter;

    public GridBoxesPanel(PickupPoint piPo) {
        this.piPo = piPo;

        setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        initPanel();
    }

    private void initPanel() {
        /*
        Potrebbe essere utile creare una classe
        Format che raggruppa informazioni sulla
        disposizione delle box come il numero di
        righe per tipologia.
         */

        setLayout(new FlowLayout());

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3, 1));

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        jp.setPreferredSize(new Dimension((screenSize.width/3), (screenSize.height/3)));

        add(jp);

        jp.add(makeGrid(piPo.getNumSmallBox()));
        jp.add(makeGrid(piPo.getNumMediumBox()));
        jp.add(makeGrid(piPo.getNumLargeBox()));


        /*
        setLayout(new GridLayout(3, 1));
        add(makeGrid(24));
        add(makeGrid(16));
        add(makeGrid(10));
        */

        /*
        Cosi` funziona, ma serve un modo per
        trovare il numero di tipologie di box.
        */
    }

    private JPanel makeGrid(int elements) {
        int rows = 4;
        while (elements%rows != 0) {
            rows -= 1;
        }

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows, elements/rows));

        for (int i = 0; i < elements; i++) {
            Box box = piPo.getBoxList().get(boxCounter);
            JButton bu = new JButton(Integer.toString(box.getCode()));
            bu.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
            if(box.isAvailable()){
                bu.setForeground(Color.GREEN);
            } else bu.setForeground(Color.RED);
            bu.setBackground(Color.YELLOW);
            grid.add(bu);
            boxCounter++;
        }

        return grid;
    }
}
