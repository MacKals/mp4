package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.animals.Hawk;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;
import ca.ubc.ece.cpen221.mp4.items.animals.Snake;
import ca.ubc.ece.cpen221.mp4.items.vehicles.HotAirBalloon;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Marauder;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Motorcycle;

/** 
 * The AI for Steve.
 * @author erikmaclennan, mkals
 */
public class SteveAI extends AbstractAI{
    
    Search search = new Search();
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
        ObjectiveFunction objective = new ObjectiveFunction(actor, search, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            if (item instanceof Snake)              objective.bad(item);
            
            else if (item instanceof Rabbit)        objective.edible(item);
            else if (item instanceof Hawk)          objective.edible(item);
            else if (item instanceof Marauder)      objective.edible(item);
            else if (item instanceof Motorcycle)    objective.edible(item);
            else if (item instanceof HotAirBalloon) objective.edible(item);
            
            else                                    objective.impartial(item);
            
        }
        
        return objective.conclusion();
    }
}
