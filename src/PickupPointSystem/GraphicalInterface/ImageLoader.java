package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;


public class ImageLoader {


    /**
     * This method ensures that the image is loaded into memory before being used,
     * blocks the execution of the code until the image is actually loaded into
     * memory.
     * @param img
     */

    public void loadImage(Image img, JPanel panel){
        MediaTracker track = new MediaTracker(panel);
        track.addImage(img,0);
        try {
            track.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
