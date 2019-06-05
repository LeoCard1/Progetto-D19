package PickupPointSystem.GraphicalInterface;

import PickupPointSystem.LockerSystem.BoxType.Box;
import PickupPointSystem.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class GridBoxesPanel extends JPanel implements Observer {
    private PickupPoint piPo;
    private static ArrayList<ButtonBox> boxes = new ArrayList<>();
    private int numBox;
    private int width;
    private int height;

    /**
     * The constructor.
     * @param piPo The pickup point.
     */

    public GridBoxesPanel(PickupPoint piPo) {
        this.piPo = piPo;
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
        setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(width*2/3, height*2/3));
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        numBox=0;
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3, 1));

        jp.setPreferredSize(new Dimension(width*5/19, height*2/5));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/14));
        add(jp);

        jp.add(makeGrid(piPo.getSmallBoxes()));
        jp.add(makeGrid(piPo.getMediumBoxes()));
        jp.add(makeGrid(piPo.getLargeBoxes()));
    }

    /**
     * A method to create the grid-like boxes panel.
     * @param elements The number of boxes of the same type (small,
     *                 medium etc.).
     * @return The panel that has been created.
     */

    private JPanel makeGrid(int elements) {
        int rows = 4;
        while (elements%rows != 0) {
            rows -= 1;
        }
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows, elements/rows));

        for (int i = 0; i < elements; i++) {
            Box box = piPo.getBoxFromIndex(numBox);
            ButtonBox bu = new ButtonBox(Integer.toString(box.getBoxNumber()));
            bu.setFont(new Font(Font.SERIF, Font.BOLD, 10));
            if(box.isAvailable()) {
                bu.setForeground(Color.decode("#228b22"));
            } else bu.setForeground(Color.RED);
            bu.setBackground(Color.decode("#FF8C00"));
            grid.add(bu);
            boxes.add(bu);
            numBox++;
        }
        grid.setBackground(Color.decode("#FF8C00"));
        return grid;
    }

    /**
     * This method checks the status of the boxes, if the status changes the icon is
     * inserted in the ButtonBox and the color of the number is changed.
     */

    private void checkState(){
        for(ButtonBox butBox : boxes){
            if(piPo.getBoxFromIndex(Integer.parseInt(butBox.getText())-1).isAvailable()){
                if(!butBox.getForeground().equals(Color.decode("#228b22"))) {
                    butBox.setIcon();
                    butBox.setForeground(Color.decode("#228b22"));
                }
            }  else {
                if(!butBox.getForeground().equals(Color.RED)) {
                    butBox.setIcon();
                    butBox.setForeground(Color.RED);
                }
            }
        }
    }

    /**
     * This method removes the icons from the boxes.
     */

    public void closeBoxes(){
        for(ButtonBox butBox : boxes){
            butBox.removeIcon();
        }
    }

    /**
     * This method sets the panel background.
     * @param g
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/locker.jpg");
        img = img.getScaledInstance(width*2/3,height*2/3,Image.SCALE_DEFAULT);
        loadImage(img);
        g.drawImage(img,0,0,this);
        super.paintComponent(g);
    }

    /**
     * This method ensures that the image is loaded into memory before being used,
     * blocks the execution of the code until the image is actually loaded into
     * memory.
     * @param img
     */
    private void loadImage(Image img){
        MediaTracker track = new MediaTracker(this);
        track.addImage(img,0);
        try {
            track.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The interface is updated after a package is added or removed.
     */

    @Override
    public void update() {
        checkState();
    }
}
