package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;

/**
 * A MoveCommand is a {@link Command} which represents a {@link Actor}
 * moving. This Command moves that actor from one space in the world to another.
 */
public final class MoveCommand implements Command {

	private final Actor actor;
	private final Location targetLocation;

	/**
	 * Construct a {@link MoveCommand}, where <code>actor</code> is the moving
	 * actor and <code>targetLocation</code> is the location that
	 * <code> actor </code> is moving to. The target location must be within
	 * <code>actor</code>'s moving range and the target location must be empty
	 * and valid.
	 *
	 * @param actor
	 *            the actor that is moving
	 * @param targetLocation
	 *            the location that actor is moving to
	 */
	public MoveCommand(Actor actor, Location targetLocation) {
		this.actor = actor;
		this.targetLocation = targetLocation;
	}

	@Override
	public void execute(World world) throws InvalidCommandException {
		// If the actor is dead, then it will not move.
		if (actor.isDead()) {
			return;
		}
		if (!Util.isValidLocation(world, targetLocation) || !Util.isLocationEmpty(world, targetLocation)) {
			throw new InvalidCommandException("Invalid MoveCommand: Invalid/non-empty target location");
		}
		if (actor.getMovingRange() < targetLocation.getDistance(actor.getLocation())) {
			throw new InvalidCommandException("Invalid MoveCommand: Target location farther than moving range");
		}

		actor.moveTo(targetLocation);
	}

}