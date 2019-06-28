package PickupPointSystem.GraphicalInterface;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to load an image into memory
 * @author Andrea Stella
 * @version 1.0
 */


public class ImageLoader {

    /**
     * This method ensures that the image is loaded into memory before being used,
     * blocks the execution of the code until the image is actually loaded into
     * memory.
     * @param img the image
     * @param panel the panel in which to add the image
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
