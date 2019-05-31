package PickupPointSystem.GraphicalInterface;

import LockerSystem.BoxType.Box;
import LockerSystem.Size;
import PickupPointSystem.PickupPoint;
import ObserverPattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;
import java.util.ArrayList;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author Sergio Gentilini
 * @version 1.0.1
 */

public class GridBoxesPanel extends JPanel implements Observer {
    private PickupPoint piPo;
    private JPanel mainPanel;
    private int numBox;
    private int width;
    private int height;
    private Image packImage;
    private Image darkImage;

    /**
     * The constructor.
     * @param piPo The pickup point.
     */

    public GridBoxesPanel(PickupPoint piPo) {
        this.piPo = piPo;
        setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        setLayout(new FlowLayout());

        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel() {
        numBox=0;
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3, 1));
        mainPanel = jp;

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        width = screenSize.width;
        height = screenSize.height;

        jp.setPreferredSize(new Dimension(width/4, height/3));
        add(jp);

        ArrayList<Box> boxList =  piPo.getBoxList();
        Size boxSize;
        Size comparingSize = piPo.getBoxSizeGivenIndex(0);
        int boxCounter = 0;
        int different;

        for (int i = 0; i < boxList.size(); i++) {
            boxSize = piPo.getBoxSizeGivenIndex(i);
            different = boxSize.compareTo(comparingSize);
            if (different != 0) {
                jp.add(makeGrid(boxCounter));
                comparingSize = piPo.getBoxSizeGivenIndex(i);
                boxCounter = 0;
            }
            boxCounter++;
        }
        jp.add(makeGrid(boxCounter));

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
            ButtonBox bu = new ButtonBox(Integer.toString(box.getCode()));
            bu.setFont(new Font(Font.SERIF, Font.BOLD, 10));
            if(box.isAvailable()){
                bu.setForeground(Color.decode("#228b22"));
            } else{
                bu.setForeground(Color.RED);
            }
            bu.setBackground(Color.decode("#FF8C00"));
            grid.add(bu);
            numBox++;
        }
        grid.setBackground(Color.decode("#FF8C00"));
        return grid;
    }

    /**
     * This method shows an image on button when clicked.
     * @param bu
     */


    /**
     * This method sets the panel background.
     * @param g
     */
    
    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/wallbackground.jpg");
        img = img.getScaledInstance(width/2,height/2,Image.SCALE_DEFAULT);
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
        remove(mainPanel);
        initPanel();
    }
}
