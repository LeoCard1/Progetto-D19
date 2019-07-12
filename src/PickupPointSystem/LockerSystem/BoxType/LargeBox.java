package PickupPointSystem.LockerSystem.BoxType;

import PickupPointSystem.LockerSystem.Size;

/**
 * This subclass of box represents the large box
 * @author Andrea Stella
 * @version 1.0
 */

public class LargeBox extends Box  {

    /**
     * The constructor. Sets the default size of the box (100,100,100)
     */

    public LargeBox(){
        super();
        super.size = new Size(100,100,100);
    }

}
