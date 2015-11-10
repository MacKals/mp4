package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.Gnat;
import ca.ubc.ece.cpen221.mp4.items.animals.SlowLoris;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Marauder;

/** 
 * The AI for the Wither.
 * @author erikmaclennan, mkals
 */
public class WitherAI extends AbstractAI {

    Search search = new Search();
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {

        
        ObjectiveFunction objective = new ObjectiveFunction(actor,search, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            if (item instanceof Grass)          objective.impartial(item);
            if (item instanceof Gnat)           objective.impartial(item);
            else if (item instanceof SlowLoris) objective.bad(item);        //Withers fears Slow Lorises with a passion
            else if (item instanceof Marauder)  objective.bad(item);        //Withers fears Slow Lorises with a passion
            else                                objective.edible(item);
            
        }
        
        return objective.conclusion();
    }
}
