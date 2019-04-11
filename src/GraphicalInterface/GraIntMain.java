package GraphicalInterface;

import Management.PickupPoint;

import javax.swing.*;
import java.awt.*;

public class GraIntMain extends JFrame {
    private PickupPoint piPo;

    public GraIntMain(PickupPoint pipo) throws HeadlessException {
        piPo = pipo;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int height = tk.getScreenSize().height;
        int width = tk.getScreenSize().width;

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
        tabP.addTab("Locker System", new JPanel());
        tabP.addTab("ViewBoxes", new ViewBoxesPanel(piPo));
    }
}
