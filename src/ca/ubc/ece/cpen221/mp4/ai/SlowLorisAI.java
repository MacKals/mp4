package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Hawk;
import ca.ubc.ece.cpen221.mp4.items.animals.Snake;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Tree;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;
import ca.ubc.ece.cpen221.mp4.items.vehicles.HotAirBalloon;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Marauder;

/** 
 * The AI for slow lorises.
 * @author erikmaclennan, mkals
 */
public class SlowLorisAI extends AbstractAI{
    
    Search search = new Search();
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
        ObjectiveFunction objective = new ObjectiveFunction(actor, search, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            if (item instanceof Wither)             objective.bad(item);
            else if (item instanceof Fox)           objective.bad(item);
            else if (item instanceof Hawk)          objective.bad(item);
            else if (item instanceof Marauder)      objective.bad(item);
            else if (item instanceof HotAirBalloon) objective.bad(item);
            else if (item instanceof Snake)         objective.bad(item);
            
            else if (item instanceof Grass)         objective.edible(item);
            else if (item instanceof Tree)          objective.edible(item);

            else                                    objective.impartial(item);
            
        }
        
        return objective.conclusion();
    }
}
