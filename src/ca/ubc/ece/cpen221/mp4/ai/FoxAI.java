package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Set;

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
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
    
	private int closest = 2; // max number; greater than fox's view range

	public FoxAI() {

	}
	
	@Override
	public Command getNextAction(ArenaWorld world, Actor actor) {
		
	    Set<Item> nearbyItems = world.searchSurroundings(actor);
	    
	    if (!nearbyItems.isEmpty()){
	        for (Item item : nearbyItems){
	            if (item.getName() == "Rabbit"){
	                actor.moveTo(new Location(item.getLocation(), Direction.North));
	                actor.attack(item);
	            }
	            if (item.getName() == "Snake"){
	                actor.attack(item);
	            }
	        } 
	    }
	    else {
	        if (actor instanceof ArenaAnimal && actor.getEnergy() >= ((ArenaAnimal) actor).getMinimumBreedingEnergy()){
	            ((ArenaAnimal) actor).breed();
	        }
	    }
	    
	    
		return new WaitCommand();
	}

}
