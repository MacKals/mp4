package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;


public class Hawk extends ArenaAnimal {

    { 
        //All item properties
    super.NAME = "Hawk";
    super.IMAGE = Util.loadImage("hawk.gif");
    super.IS_MEAT = true;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 60;
    super.STRENGTH = 60;
    
    //All Actor properties
    super.INITIAL_ENERGY = 40;
    super.VIEW_RANGE = 5;
    super.COOLDOWN_PERIOD = 1;
    super.MOVING_RANGE = 3;
    
    //ArenaAnimal specific property
    super.MIN_BREEDING_ENERGY = 20;
    
    }
    

    /**
     * Create a new {@link Rabbit} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param rabbitAI
     *            : The AI designed for rabbits
     * @param initialLocation
     *            : the location where this rabbit will be created
     */
    public Hawk(AI HawkAI, Location initialLocation) {
        ai = HawkAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    @Override
    public ArenaAnimal breed() {
        Hawk child = new Hawk(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }
}
