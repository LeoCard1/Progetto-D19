package PickupPointSystem.LockerSystem.BoxType;

import PickupPointSystem.LockerSystem.Size;

/**
 * This subclass of box represents the large box
 * @author Andrea Stella
 * @version 1.0
 */

public class SmallBox extends Box {

    /**
     * The constructor. Sets the default size of the box (30,30,30)
     */

    public SmallBox(){
        super();
        super.size = new Size(30,30,30);
    }

}
