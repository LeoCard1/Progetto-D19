package PickupPointSystem.GraphicalInterface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

public class StartPanel extends JPanel {

    private int height;
    private int width;
    private BackGroundPanel bgp;

    /**
     * The constructor.
     * @param bgp
     */

    public StartPanel(BackGroundPanel bgp){
        this.bgp = bgp;
        Toolkit tk = getDefaultToolkit();
        height = tk.getScreenSize().height;
        width = tk.getScreenSize().width;
        setPreferredSize(new Dimension(width*2/3, height*2/3));
        initPanel();
    }

    /**
     * This method initialises the panel.
     */

    private void initPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER,0,height/2));
        add(createButtonStart());
    }

    /**
     * This method creates the buttonStart and adds a gif as an icon,
     * when the button is clicked, the panel is changed to boxAccessPanel.
     * @return JButton
     */

    private JButton createButtonStart(){
        Image img =  getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/touch.gif");
        img = img.getScaledInstance(width/5, height/10,2);
        JButton buttonStart = new JButton();
        buttonStart.setIcon(new ImageIcon(img));
        buttonStart.setOpaque(false);
        buttonStart.setContentAreaFilled(false);
        buttonStart.setBorderPainted(false);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgp.changePanel("boxAccessPanel");
            }
        });
        return buttonStart;

    }

    /**
     * This method sets the panel background.
     * @param g
     */

    @Override
    public void paintComponent(Graphics g){
        setOpaque(false);
        Image img = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/startbackground.jpg");
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

}
