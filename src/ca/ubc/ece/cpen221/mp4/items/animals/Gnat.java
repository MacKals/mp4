package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;

/**
 * This is a simple implementation of a Gnat. It never loses energy and moves in
 * random directions.
 */
public class Gnat extends ArenaAnimal {
    
//    
//    private String name;
//    protected Location location;
//    private ImageIcon image;
//    
//    protected int MAX_ENERGY;
//    protected int energy;
//    private int STRENGTH;
    
	super.image = Util.loadImage("gnat.gif");
	super.MEAT_CALORIES = 100;
	super.PLANT_CALORIES = 0;
	super.STRENGTH = 10;
	super.energy = 100;
	super.movingRange = 1;
	
	super.name = "Gnat";


	/**
	 * Create a new Gnat at <code>initialLocation</code>. The
	 * <code>initialLocation</code> must be valid and empty.
	 *
	 * @param initialLocation
	 *            the location where the Gnat will be created
	 */
	public Gnat(Location initialLocation) {
		super.location = initialLocation;
		this.isDead = false;
	}

	public ArenaAnimal breed(){
	   return null;    
	}
	
	
	@Override
	public void loseEnergy(int energy) {
		isDead = true; // Dies if it loses energy.
	}

	@Override
	public int getCoolDownPeriod() {
		// Each Gnat acts every 1-10 steps randomly.
		return Util.RAND.nextInt(10) + 1;
	}

	@Override
	public Command getNextAction(World world) {
		// The Gnat selects a random direction and check if the next location at
		// the direction is valid and empty. If yes, then it moves to the
		// location, otherwise it waits.
		Direction dir = Util.getRandomDirection();
		Location targetLocation = new Location(this.getLocation(), dir);
		if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
			return new MoveCommand(this, targetLocation);
		}

		return new WaitCommand();
	}
}
