package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;


import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Grass;


/**
 * The {@link Rabbit} is an {@link ArenaAnimal} that eats {@link Grass} and can
 * be eaten by {@link Fox}.
 */
public class Rabbit extends ArenaAnimal {

    { 
        //All item properties
    super.NAME = "Rabbit";
    super.IMAGE = Util.loadImage("rabbit.gif");
    super.IS_MEAT = true;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 60;
    super.STRENGTH = 60;
    
    //All Actor properties
    super.INITIAL_ENERGY = 40;
    super.VIEW_RANGE = 3;
    super.COOLDOWN_PERIOD = 2;
    super.MOVING_RANGE = 1;
    
    //ArenaAnimal specific property
    super.MIN_BREEDING_ENERGY = 10;
    
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
	public Rabbit(AI rabbitAI, Location initialLocation) {
		this.ai = rabbitAI;
		this.location = initialLocation;
		this.energy = INITIAL_ENERGY;
	}

	@Override
	public ArenaAnimal breed() {
		Rabbit child = new Rabbit(ai, location);
		child.energy = energy / 2;
		this.energy = energy / 2;
		return child;
	}
}
