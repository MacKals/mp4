package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Gnat;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;
import ca.ubc.ece.cpen221.mp4.items.animals.Snake;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;

public class HawkAI extends AbstractAI{
    
    private int MOVING_RANGE = 0;
    
    public HawkAI() {
        
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
        
        ObjectiveFunction objective = new ObjectiveFunction(actor); 
        
        Set<Item> visibleItemsSet = world.searchSurroundings(actor);

        for (Item item : visibleItemsSet) {
            
            if (item instanceof Snake) {
                objective.edible(item);
            } else if (item instanceof Rabbit) {
                objective.edible(item);
            } else {
                objective.impartial(item);
            }
        }
        
        return objective.conclusion();
    }
}