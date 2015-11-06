package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;


/**
 * The {@link Fox} is an {@link ArenaAnimal} that tries to eat {@link Rabbit}s.
 */
public class Fox extends ArenaAnimal {

    { 
        //All item properties
    super.name = "Fox";
    super.image = Util.loadImage("fox.gif");
    super.PLANT_CALORIES = 0;
    super.MEAT_CALORIES = 60;
    super.MAX_ENERGY = 120;
    
        //protected int energy;
    super.STRENGTH = 100;
    
    //All Actor properties
    super.INITIAL_ENERGY = 100;
    super.VIEW_RANGE = 5;

    super.coolDownPeriod = 3;
    super.movingRange = 1;
    
    //ArenaAnimal specific property
    super.MIN_BREEDING_ENERGY = 20;
    
    }
	
	/**
	 * Create a new {@link Fox} with an {@link AI} at
	 * <code>initialLocation</code>. The <code> initialLocation </code> must be
	 * valid and empty
	 *
	 * @param foxAI
	 *            the AI designed for foxes
	 * @param initialLocation
	 *            the location where this Fox will be created
	 */
	public Fox(AI foxAI, Location initialLocation) {
		this.ai = foxAI;
		this.location = initialLocation;

		this.energy = INITIAL_ENERGY;
	}

	@Override
	public ArenaAnimal breed() {
		Fox child = new Fox(ai, location);
		child.energy = energy / 2;
		this.energy = energy / 2;
		return child;
	}
}
