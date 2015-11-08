package ca.ubc.ece.cpen221.mp4.items;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AbstractAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Tree;

/**
 * The Gardener does not show up in the world, but it plants a {@link Grass} at
 * a random location each step if more than half of the world's locations are
 * empty. Don't worry, Grass doesn't just appear...
 */
public class Gardener extends Actor {

    private int stepCounter = 0;
    private final int STEPS_PER_TREE = 5;
    
	public int getCoolDownPeriod() {
		// Acts every step.
		return 1;
	}

	@SuppressWarnings("unused")
	public Command getNextAction(World world) {
		int occupiedLocations = 0;
		for (Item item : world.getItems()) {
			occupiedLocations++;
		}

		// If the number of occupied locations is less than half of the total
		// number of locations, this Gardener plants Grass at a random location.
		int totalLocations = world.getHeight() * world.getWidth();
		if (occupiedLocations < totalLocations / 2) {
			final Location grassLoc = Util.getRandomEmptyLocation(world);
			
			// An anonymous Command class which plants grass.
			return new Command() {
				@Override
				public void execute(World world) {
					world.addItem(new Grass(grassLoc));
					if (stepCounter % STEPS_PER_TREE == 0){ // one tree added every X steps
					    world.addItem(new Tree(Util.getRandomEmptyLocation(world)));
					}
					stepCounter++;
				}
			};

		}

		// Else it does nothing at all.
		return new WaitCommand();
	}


}
