package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.SnakeAI;

/**
 * The snake is an elongated, legless reptile. It is carnivorous,
 *  and can eat animals similar in size to itself.
 * @author erikmaclennan, mkals
 */
public class Snake extends ArenaAnimal {

    { 
        //All item properties
    super.NAME = "Snake";
    super.IMAGE = Util.loadImage("snake.gif");
    super.IS_MEAT = true;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 120;
    super.STRENGTH = 90;
    
    //All Actor properties
    super.INITIAL_ENERGY = 100;
    super.VIEW_RANGE = 5;
    super.COOLDOWN_PERIOD = 3;
    super.MOVING_RANGE = 1;
    
    //ArenaAnimal specific property
    super.MIN_BREEDING_ENERGY = 20;
    
    }
    

    /**
     * Create a new {@link Snake} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param SnakeAI
     *            : The AI designed for snakes
     * @param initialLocation
     *            : the location where this snake will be created
     */
    public Snake(AI SnakeAI, Location initialLocation) {
        ai = SnakeAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    @Override
    public ArenaAnimal breed() {
        Snake child = new Snake(new SnakeAI(), location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }

}
