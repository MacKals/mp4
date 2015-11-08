package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {
    
    private int MOVING_RANGE = 0;
    
    public RabbitAI() {
        
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
            
            if (item instanceof Fox) {
                objective.bad(item);
            } else if (item instanceof Grass) {
                objective.edible(item);
            } else {
                objective.impartial(item);
            }
        }
        
        return objective.conclusion();
    }
}

    /*
    private Direction directionTowards(boolean towards, Actor actor, Item opponent, Location[] takenLocations) {

        int actorX = actor.getLocation().getX();
        int actorY = actor.getLocation().getY();

        int opponentX = opponent.getLocation().getX();
        int opponentY = opponent.getLocation().getY();

        int deltaX = actorX - opponentX;
        int deltaY = actorY - opponentY;

        Direction[] bestDirections = bestDirection(deltaX, deltaY);

        for (Direction direction : bestDirections) {
            if (!takenLocations.equals(new Location(actor.getLocation(), direction))) {
                return direction;
            }
        }
        
        return null;
    }

    private Location locationInDirection(Direction direction, Actor actor, int numberOfSteps) {

        switch (direction) {
        case North:
            return new Location(actor.getLocation().getX(), actor.getLocation().getY() - numberOfSteps);
        case East:
            return new Location(actor.getLocation().getX() + numberOfSteps, actor.getLocation().getY());
        case South:
            return new Location(actor.getLocation().getX(), actor.getLocation().getY() + numberOfSteps);
        case West:
            return new Location(actor.getLocation().getX() - numberOfSteps, actor.getLocation().getY());
        }

        return null;
    }
}
*/
/*
 * 
 * if (deltaX > 0) { if (deltaY > 0) { if (deltaX > deltaY) { return
 * Direction.West; } else { return Direction.North; } } else if (deltaY == 0) {
 * return Direction.West; } else { if (deltaX > -deltaY) { return
 * Direction.West; } else { return Direction.South; } } } else { if (deltaY > 0)
 * { if (-deltaX > deltaY) { return Direction.East; } else { return
 * Direction.North; } } else if (deltaY == 0) { return Direction.East; else { if
 * (-deltaX > -deltaY) { return Direction.East; } else { return Direction.South;
 * } } }
 */
