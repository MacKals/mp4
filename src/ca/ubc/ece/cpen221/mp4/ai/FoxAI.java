package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;

/** 
 * The AI for foxes.
 * @author erikmaclennan, mkals
 */
public class FoxAI extends AbstractAI {
    
    private Search search = new Search();

    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
        ObjectiveFunction objective = new ObjectiveFunction(actor, search, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            
            if (item instanceof Wither)             objective.bad(item);
            
            else if (item instanceof SlowLoris)     objective.edible(item);
            else if (item instanceof Rabbit)        objective.edible(item);
            else if (item instanceof Snake)         objective.edible(item);
            //else if (item instanceof Gnat)          objective.edible(item);
            
            else                                    objective.impartial(item);
            
        }
        
        return objective.conclusion();
    }
}

