package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

public class ButtonBox extends JButton {

    public ButtonBox(String boxCode){
        super(boxCode);
        
        Image packImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/pack.jpg");
        Image emptyBoxImage = getDefaultToolkit().createImage("src/PickupPointSystem/GraphicalInterface/Icons/emptybox.jpg");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image img;
                if(getIcon()!=null){
                    setIcon(null);
                    return;
                }
                if(getForeground()==Color.RED){
                    img = packImage;
                }  else {
                    img = emptyBoxImage;
                }
                img = img.getScaledInstance(getWidth()+8, getHeight()-3,Image.SCALE_DEFAULT);
                setIcon(new ImageIcon(img));
            }
        });
    }

}
