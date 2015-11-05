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

    //All item properties
    private String name = "Rabbit";
    protected Location location;
    private static final ImageIcon image = Util.loadImage("rabbit.gif");
    protected int PLANT_CALORIES = 0;
    protected int MEAT_CALORIES ;
    protected int MAX_ENERGY = 60;
        //protected int energy;
    private int STRENGTH = 60;
    
    //All Actor properties
	private static final int INITIAL_ENERGY = 40;
	private static final int VIEW_RANGE = 3;
	private static final int COOLDOWN = 2;

	private int coolDownPeriod = 1;
	private final AI ai;
	private int movingRange = 1;
	
	//ArenaAnimal specific property
	private static final int MIN_BREEDING_ENERGY = 10;
	

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
		ai = rabbitAI;
		location = initialLocation;
		energy = INITIAL_ENERGY;
	}

	@Override
	public ArenaAnimal breed() {
		Rabbit child = new Rabbit(ai, location);
		child.energy = energy / 2;
		this.energy = energy / 2;
		return child;
	}
}
