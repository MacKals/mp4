package ca.ubc.ece.cpen221.mp4;

import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public class Actor extends Item implements ActorInterface {

    
    protected int coolDownPeriod = 1;
    protected AI ai;
    protected int movingRange = 1;
    protected int VIEW_RANGE = 3;
    protected int INITIAL_ENERGY = 40;
    protected boolean isDead = false;
    
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
    
    @Override
    public void moveTo(Location location){
        this.location = location;
    }

    @Override
    public void attack(Item enemy) {
       enemy.loseEnergy(this.getStrength());
       
    }
}
