package ca.ubc.ece.cpen221.mp4;

import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;

/**
 * An Actor is an object in a {@link World} that can actively affect the state
 * of that World. It has a cooldown period which defines how often it performs
 * an action.
 */
public class Actor extends Item {

    protected int coolDownPeriod = 1;
    protected AI ai;
    protected int movingRange = 1;
    protected int VIEW_RANGE = 3;
    protected int INITIAL_ENERGY = 40;
    protected boolean isDead = false;
    
    /**
     * Returns the cooldown period between two consecutive actions. This
     * represents the number of steps passed between two actions.
     *
     * @return the number of steps between actions
     */
    public int getCoolDownPeriod() {
        return coolDownPeriod;
    }

    /**
     * Returns the next action to be taken, given the current state of the
     * world.
     *
     * @param world
     *            the current world
     * @return the next action of this Actor as a {@link Command}
     */
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.
        return nextAction;
    }
    
    /**
     * Returns the maximum distance that this item can move in one step. For
     * example, a {@link MoveableItem} with moving range 1 can only move to
     * adjacent locations.
     *
     * @return the maximum moving distance
     */
    public int getMovingRange() {
        return movingRange; // Can only move to adjacent locations.
    }

    /**
     * Returns the range of the animal's vision. The range is measured in
     * Manhattan Distance, for example, if an animal has view range of 2, then
     * it can see all valid locations in the rectangle
     * {(x-2,y-2),(x+2,y-2),(x-2,y+2),(x+2,y+2)}, where (x,y) are the
     * coordinates of its current location.
     *
     * @return the view range of this animal
     */
    public int getViewRange() {
        return VIEW_RANGE;
    }
    
    /**
     * Move to the target location. The <code> targetLocation </code> must be
     * valid and empty.
     *
     * @param targetLocation
     *            the location that this item is moving to
     */
    public void moveTo(Location location){
        this.location = location;
    }

    //TODO: comment
    public void attack(Item enemy) {
       enemy.loseEnergy(this.getStrength());
    }
}
