package GraphicalInterface;

import Management.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;

public class GraIntMain extends JFrame implements Observer {
    private PickupPoint piPo;
    private ViewBoxesPanel viewBoxesPanel;
    private int height;
    private int width;

    public GraIntMain(PickupPoint pipo) throws HeadlessException {
        piPo = pipo;
        Toolkit tk = Toolkit.getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;

        setSize(width/2, height/2);
        setLocation(width/4, height/4);

        setTitle("Pickup Point System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initPanel();
    }

    private void initPanel() {
        JTabbedPane tabP = new JTabbedPane();
        add(tabP);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/8));
        jp.add(new BoxAccessPanel(piPo));

        tabP.addTab("Locker System", jp);
        viewBoxesPanel = new ViewBoxesPanel(piPo);
        tabP.addTab("View Boxes", viewBoxesPanel);
    }

    @Override
    public void update() {
        viewBoxesPanel.update();
    }
}
