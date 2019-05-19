package Management.GraphicalInterfaceManager;

import Management.Manager;

import javax.swing.*;
import java.awt.*;

public class ManagerFrame extends JFrame {

    private Manager manager;

    public ManagerFrame(Manager manager){
        this.manager = manager;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int height = tk.getScreenSize().height;
        int width = tk.getScreenSize().width;

        setSize(width/2, height/2);
        setLocation(width/4, height/4);

        setTitle("Manager System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initPanel();
    }

    private void initPanel(){
        JTabbedPane tabPan = new JTabbedPane();
        add(tabPan);
        tabPan.addTab("New DeliveryMan",new NewDMPanel(manager));
        tabPan.addTab("Add Pack To DeliveryMan",new AddPackDMPanel(manager));
        tabPan.addTab("View Packages", new ViewPackPanel(manager));
        tabPan.addTab("View Delivery Man", new ViewDMPanel(manager));


    }
}
