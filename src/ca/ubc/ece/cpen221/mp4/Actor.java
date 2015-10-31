package ca.ubc.ece.cpen221.mp4;

import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public abstract class Actor extends Item implements ActorInterface {

    
    private int coolDownPeriod;
    private AI ai;
    private int movingRange;
    
    private int VIEW_RANGE;
    private int COOLDOWN;

    
    @Override
    public int getCoolDownPeriod() {
        return coolDownPeriod;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.
        return nextAction;
    }
    
    @Override
    public int getMovingRange() {
        return movingRange; // Can only move to adjacent locations.
    }

    @Override
    public int getViewRange() {
        return VIEW_RANGE;
    }
}
