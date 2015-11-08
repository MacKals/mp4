package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.animals.SlowLoris;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;

public class MarauderAI extends AbstractAI {

    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
        ObjectiveFunction objective = new ObjectiveFunction(actor, world); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            
            if (item instanceof Wither)             objective.edible(item);
            else if (item instanceof SlowLoris)     objective.bad(item);
            else                                    objective.impartial(item);
            
        }
        
        return objective.conclusion();
    }
}
