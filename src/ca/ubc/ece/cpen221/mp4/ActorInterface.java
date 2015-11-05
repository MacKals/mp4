package ca.ubc.ece.cpen221.mp4;

import ca.ubc.ece.cpen221.mp4.commands.Command;

/**
 * An Actor is an object in a {@link World} that can actively affect the state
 * of that World. It has a cooldown period which defines how often it performs
 * an action.
 */
public interface ActorInterface {

	/**
	 * Returns the cooldown period between two consecutive actions. This
	 * represents the number of steps passed between two actions.
	 *
	 * @return the number of steps between actions
	 */
	int getCoolDownPeriod();

	/**
	 * Returns the next action to be taken, given the current state of the
	 * world.
	 *
	 * @param world
	 *            the current world
	 * @return the next action of this Actor as a {@link Command}
	 */
	Command getNextAction(World world);
	
	/**
	 * Returns the maximum distance that this item can move in one step. For
	 * example, a {@link MoveableItem} with moving range 1 can only move to
	 * adjacent locations.
	 *
	 * @return the maximum moving distance
	 */
    public int getMovingRange();

    
    /**
     * Returns the range of the animal's vision. The range is measured in
     * Manhattan Distance, for example, if an animal has view range of 2, then
     * it can see all valid locations in the rectangle
     * {(x-2,y-2),(x+2,y-2),(x-2,y+2),(x+2,y+2)}, where (x,y) are the
     * coordinates of its current location.
     *
     * @return the view range of this animal
     */
    int getViewRange();
    
    // TODO
    public void attack(Item enemy);
    
    /**
     * Move to the target location. The <code> targetLocation </code> must be
     * valid and empty.
     *
     * @param targetLocation
     *            the location that this item is moving to
     */
    void moveTo(Location location);

}