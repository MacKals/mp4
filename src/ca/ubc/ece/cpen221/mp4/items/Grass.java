package ca.ubc.ece.cpen221.mp4.items;

import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

/**
 * Grass will by planted by the {@link Gardener} every step at an empty location
 * if fewer than half of all locations in the world are occupied.
 */
public class Grass extends Item {

    {
    //All item properties
    super.NAME = "grass";
    super.IMAGE = Util.loadImage("grass.gif");
    super.IS_MEAT = true;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 10;
    super.STRENGTH = 5;
    }
    
    /**
     * Plant a Grass at <code> location </code>. The location must be valid and
     * empty
     *
     * @param location
     *            : the location where this grass will be created
     */
    public Grass(Location initialLocation) {
        this.location = initialLocation;
        this.energy = MAX_ENERGY;
    }

}