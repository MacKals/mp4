package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Random;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Hawk;
import ca.ubc.ece.cpen221.mp4.items.animals.Snake;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Steve;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Marauder;

/**
 * Our Rabbit AI.
 */
public class RabbitAI extends AbstractAI {
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
        if (getSearchGoal() == null) {
            
            Goal goal  = Goal.Centre;
            
            Random generator = new Random();
            
            switch (generator.nextInt(5)) {
            case 0: goal = Goal.NE;
            case 1: goal = Goal.SE;
            case 2: goal = Goal.SW;
            case 3: goal = Goal.NW;
            case 4: goal = Goal.Centre;
            }

            setSearchGoal(goal);
        }
        
        ObjectiveFunction objective = new ObjectiveFunction(actor, this, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            if (item instanceof Fox)            objective.bad(item);
            if (item instanceof Hawk)           objective.bad(item);
            if (item instanceof Snake)          objective.bad(item);
            if (item instanceof Steve)          objective.bad(item);
            if (item instanceof Wither)         objective.bad(item);
            if (item instanceof Marauder)       objective.bad(item);
            
            else if (item instanceof Grass)     objective.edible(item);
            
            else                                objective.impartial(item);
            
        }
        
        return objective.conclusion();
        
    }
}