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
 * The AI for rabbits.
 * @author erikmaclennan, mkals
 */
public class RabbitAI extends AbstractAI {
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
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