package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.AttackCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
    
    private int MOVING_RANGE = 0;
    
    public FoxAI() {
        
    }
    
    private void initFromActor(Actor actor) {
        MOVING_RANGE = actor.getMovingRange();
    }
    
    enum ItemClass {
        Food, Preditor, Uninterested;
    }
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {

        initFromActor(actor);
        
        ObjectiveFunction objective = new ObjectiveFunction(actor, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            if (item instanceof Wither) {
                objective.bad(item);
            } else if (item instanceof SlowLoris) {
                objective.edible(item);
            } else if (item instanceof Rabbit) {
                objective.edible(item);
            } else if (item instanceof Snake) {
                objective.edible(item);
            }else {
                objective.impartial(item);
            }
        }
        
        return objective.conclusion();
    }
}

