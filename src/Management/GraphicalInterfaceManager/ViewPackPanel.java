package Management.GraphicalInterfaceManager;

import Management.Manager;
import ObserverPattern.Observer;
import LockerSystem.Package;
import PickupPointSystem.GraphicalInterface.ErrorGUI.ErrorGUIMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewPackPanel extends JPanel implements Observer {

    private Manager manager;
    private JComboBox filterBox = new JComboBox();
    private JTextArea packArea = new JTextArea();
    private ArrayList<String> filters = new ArrayList<>();

    public ViewPackPanel(Manager manager){
        this.manager = manager;
        manager.addObserver(this);
        initPanel();
    }

    public void initPanel(){
        filters.add("All");
        filters.add("Delivered");
        filters.add("Unassigned");
        filters.add("In transit");
        
        for(String filter : filters){
            filterBox.addItem(filter);
        }

        JLabel packDetails = new JLabel("packID          height             length              width               password");
        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePackText();
            }
        });

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,1));
        p1.add(filterBox);  p1.add(buttonConfirm);


        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(p1,BorderLayout.NORTH);


        JPanel p21 = new JPanel();
        p21.setLayout(new BorderLayout());
        p21.add(p2, BorderLayout.WEST); p21.add(new JPanel(), BorderLayout.CENTER);

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(packDetails, BorderLayout.NORTH);   p3.add(packArea, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(p21, BorderLayout.WEST);  add(p3, BorderLayout.CENTER);


    }

    public void updatePackText(){
        String text = "";
        packArea.setText("");
        switch (filterBox.getSelectedItem().toString()){
            case "All":
                for(Package pack : manager.getPackagesList()){
                    text +=pack.toString()+"\n";
                    packArea.setText(text);
                }
                break;
            case "Delivered":
                for(Package pack : manager.getPackagesList()){
                    String password = manager.getPasswordList().get(pack.getId());
                    if(password != null){
                        text+=pack.toString()+"\t"+password+"\n";
                        packArea.setText(text);
                    }
                }
                break;
            case "Unassigned":
                for(Package pack : manager.getUnassignedPackagesList()){
                    text+=pack.toString()+"\n";
                    packArea.setText(text);
                }
                break;
            case "In transit":
                for(Package pack : manager.getInTransitPackagesList()){
                    text+=pack.toString()+"\n";
                    packArea.setText(text);
                }
                break;
            default:
                ErrorGUIMain guiError = new ErrorGUIMain("Unable to update pack text!", true);
        }
    }

    @Override
    public void update() {
        updatePackText();
    }
}
