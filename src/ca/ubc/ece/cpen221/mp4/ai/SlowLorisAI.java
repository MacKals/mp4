package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Gnat;
import ca.ubc.ece.cpen221.mp4.items.animals.Snake;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;

/** 
 * The AI for slow lorises.
 * @author erikmaclennan, mkals
 */
public class SlowLorisAI extends AbstractAI{
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
        ObjectiveFunction objective = new ObjectiveFunction(actor, this, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            if (item instanceof Wither)         objective.bad(item);
            else if (item instanceof Fox)       objective.bad(item);
            else if (item instanceof Gnat)      objective.edible(item);
            else if (item instanceof Snake)     objective.edible(item);
            else if (item instanceof Snake)     objective.edible(item);
            else                                objective.impartial(item);
            
        }
        
        return objective.conclusion();
    }
}
