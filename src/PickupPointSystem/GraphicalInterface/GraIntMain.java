package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;

public class GraIntMain extends JFrame implements Observer {
    private PickupPoint piPo;
    private ViewBoxesPanel viewBoxesPanel;
    private int height;
    private int width;
    private JTabbedPane t; // variabile necessaria per cambiare la lingua delle tab (più informazioni nella classe SetLanguage)

    public GraIntMain(PickupPoint pipo) throws HeadlessException {
        piPo = pipo;
        Toolkit tk = Toolkit.getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;

        setSize(width/2, height/2);
        setLocation(width/4, height/4);

        setTitle(SetLanguage.getInstance().setGraIntMain()[0]);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initPanel();
    }

    private void initPanel() {
        JTabbedPane tabP = new JTabbedPane();
        add(tabP);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/8));
        jp.add(new BoxAccessPanel(piPo, this));

        tabP.addTab(SetLanguage.getInstance().setGraIntMain()[1], jp);
        viewBoxesPanel = new ViewBoxesPanel(piPo);
        tabP.addTab(SetLanguage.getInstance().setGraIntMain()[2], viewBoxesPanel);

        t = tabP;
    }

    public void refresh() {
        setTitle(SetLanguage.getInstance().setGraIntMain()[0]);
        t.setTitleAt(0, SetLanguage.getInstance().setGraIntMain()[1]);
        t.setTitleAt(1, SetLanguage.getInstance().setGraIntMain()[2]);
    }

    @Override
    public void update() {
        viewBoxesPanel.update();
    }
}
