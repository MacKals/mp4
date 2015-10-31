package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

/**
 * A BreedCommand is a {@link Command} which represents a {@link LivingItem}
 * breeding. This Command inserts a 'child' into the given World.
 */
public final class BreedCommand implements Command {

	private final ArenaAnimal animal;
	private final Location target;

	/**
	 * Constructor where <code>item</code> is the LivingItem that is breeding
	 * and <code>target</code> is the location where the child will appear. The
	 * child must be born at an empty location adjacent to the parent.
	 *
	 * @param item
	 *            the parent LivingItem
	 * @param target
	 *            the location where child will appear
	 */
	public BreedCommand(ArenaAnimal animal, Location target) {
		this.animal = animal;
		this.target = target;
	}

	@Override
	public void execute(World world) throws InvalidCommandException {
		if (!Util.isValidLocation(world, target) || !Util.isLocationEmpty(world, target)) {
			throw new InvalidCommandException("Invalid BreedCommand: Invalid/non-empty breeding target location");
		}

		ArenaAnimal child = animal.breed();
		child.moveTo(target);
		world.addItem(child);
		world.addActor(child);
	}

}
