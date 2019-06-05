package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class GraIntMain extends JFrame implements Observer {

    private PickupPoint piPo;
    private BackGroundPanel backGroundPanel;
    private int height;
    private int width;

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public GraIntMain(PickupPoint pipo){
        piPo = pipo;
        Toolkit tk = Toolkit.getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;

        setSize(width*2/3, height*3/4);
        setLocation(width/2, height/2);

        setResizable(false);
        setTitle(SetLanguage.getInstance().setGraIntMain()[0]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initPanel();
        setVisible(true);
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        setTheme("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        backGroundPanel = new BackGroundPanel(piPo,this);
        add(backGroundPanel);

    }

    /**
     * This method changes the frame theme.
     * @param className
     */

    public void setTheme(String className){
        try {
            UIManager.setLookAndFeel(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refresh after changing the language.
     */

    public void refresh() {
        setTitle(SetLanguage.getInstance().setGraIntMain()[0]);
        backGroundPanel.refresh();
    }

    /**
     * The interface is updated after a package is added or removed.
     */

    @Override
    public void update() {
        backGroundPanel.update();
        revalidate();
    }
}
