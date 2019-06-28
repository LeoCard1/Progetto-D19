package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.Exceptions.IncorrectIDException;
import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;
import PickupPointSystem.GraphicalInterface.LoadingGUI.LoadingGUIMain;
import PickupPointSystem.PickupPoint;
import PickupPointSystem.ObserverPattern.Observer;
import PickupPointSystem.Server.PickupPointServer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class GraIntMain extends JFrame {

    private PickupPoint piPo;
    private BackGroundPanel backGroundPanel;
    private LoadingGUIMain loadingGUIMain = new LoadingGUIMain();
    private int height;
    private int width;

    /**
     * The constructor.
     * @param pipo The pickup point.
     */

    public GraIntMain(String piPoID){

        if(!createPickupPoint(piPoID)){
            return;
        }

        loadingGUIMain.setVisible(true);
        loadingGUIMain.setText("Graphical Interface Creation...");

        Toolkit tk = Toolkit.getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;

        setSize(width*2/3, height*4/5);
        setLocationRelativeTo(null);

        setResizable(false);
        setTitle(SetLanguage.getInstance().setGraIntMain()[0]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initPanel();
        setVisible(true);

        loadingGUIMain.dispose();
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
     * This method creates the Pickup Point from the id passed as an argument
     * and updates the box
     * @param piPoID the pickup point id
     * @return true if the creation and update was successful, else false
     */

    public boolean createPickupPoint(String piPoID){

        loadingGUIMain.setVisible(true);
        loadingGUIMain.setText("Pickup Point Creation...");
        try {
            piPo = new PickupPoint(piPoID);
            loadingGUIMain.setText("Updating Box...");
            piPo.updateBox();
            loadingGUIMain.dispose();
        } catch(IOException e){
            loadingGUIMain.dispose();
            new ErrorGUIMain("Unable To Connect To Server", true);
            return false;
        } catch (IncorrectIDException e){
            loadingGUIMain.dispose();
            new ErrorGUIMain("Incorrect Pickup Point ID", true);
            return false;
        }
        return true;
    }


}
