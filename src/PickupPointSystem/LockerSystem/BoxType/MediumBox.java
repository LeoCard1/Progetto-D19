package PickupPointSystem.LockerSystem.BoxType;

import PickupPointSystem.LockerSystem.Size;

/**
 * This subclass of box represents the medium box
 * @author Andrea Stella
 * @version 1.0
 */

public class MediumBox extends Box {

    /**
     * The constructor. Sets the default size of the box (60,60,60)
     */

    public MediumBox(){
        super();
        super.size = new Size(60,60,60);
    }

}
