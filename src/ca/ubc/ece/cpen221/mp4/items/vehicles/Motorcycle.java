package ca.ubc.ece.cpen221.mp4.items.vehicles;


import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;

public class Motorcycle extends Actor {

    {
        
    //All item properties
    super.NAME = "Motocycle";
    super.IMAGE = Util.loadImage("motorcycle.gif");
    super.IS_MEAT = false;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 120;
    super.STRENGTH = 400;
    
    //All Actor properties
    super.INITIAL_ENERGY = 100;
    super.VIEW_RANGE = 15;
    super.COOLDOWN_PERIOD = 3;
    super.MOVING_RANGE = 1;
    }
    
    /**
     * Create a new {@link Motorcycle} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param vehicleAI
     *            : The AI designed for vehicles
     * @param initialLocation
     *            : the location where this motorcycle will be created
     */
    public Motorcycle(AI vehicleAI, Location initialLocation) {
        this.ai = vehicleAI;
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
    }

}
