package GraphicalInterface;

import LockerSystem.BoxType.Box;
import LockerSystem.Size;
import Management.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridBoxesPanel extends JPanel implements Observer {
    private PickupPoint piPo;
    private JPanel pannelloPrincipale;

    public GridBoxesPanel(PickupPoint piPo) {
        this.piPo = piPo;

        setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        setLayout(new FlowLayout());

        initPanel();
    }

    private void initPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3, 1));
        pannelloPrincipale = jp;

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        jp.setPreferredSize(new Dimension((screenSize.width/3), (screenSize.height/3)));

        add(jp);

        /*jp.add(makeGrid(piPo.getNumSmallBox()));
        jp.add(makeGrid(piPo.getNumMediumBox()));
        jp.add(makeGrid(piPo.getNumLargeBox()));*/

        ArrayList<Box> boxList =  piPo.getBoxList();
        Size boxSize = boxList.get(0).getSize();
        int boxCounter = 0;
        int different;

        for (int i = 0; i < boxList.size(); i++) {
            different = boxList.get(i).getSize().compareTo(boxSize);
            if (different != 0) {
                jp.add(makeGrid(boxCounter));
                boxSize = boxList.get(i).getSize();
                boxCounter = 0;
            }
            boxCounter++;
        }
        jp.add(makeGrid(boxCounter));
    }

    private JPanel makeGrid(int elements) {
        int rows = 4;
        while (elements%rows != 0) {
            rows -= 1;
        }

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows, elements/rows));

        for (int i = 0; i < elements; i++) {
            Box box = piPo.getBoxList().get(i);
            JButton bu = new JButton(Integer.toString(box.getCode()));
            bu.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
            if(box.isAvailable()){
                bu.setForeground(Color.GREEN);
            } else bu.setForeground(Color.RED);
            bu.setBackground(Color.YELLOW);
            grid.add(bu);
        }

        return grid;
    }

    @Override
    public void update() {
        remove(pannelloPrincipale);
        initPanel();
    }
}
