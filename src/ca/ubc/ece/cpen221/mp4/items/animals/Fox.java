package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.FoxAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;


/**
 * The {@link Fox} is an {@link ArenaAnimal} that tries to eat {@link Rabbit}s.
 */
public class Fox extends ArenaAnimal {

    { 
        //All item properties
    super.NAME = "Fox";
    super.IMAGE = Util.loadImage("fox.gif");
    super.IS_MEAT = true;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 120;
    super.STRENGTH = 100;
    
    //All Actor properties
    super.INITIAL_ENERGY = 100;
    super.VIEW_RANGE = 5;
    super.COOLDOWN_PERIOD = 3;
    super.MOVING_RANGE = 1;
    
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
		Fox child = new Fox(new FoxAI(), location);
		child.energy = energy / 2;
		this.energy = energy / 2;
		return child;
	}
}
