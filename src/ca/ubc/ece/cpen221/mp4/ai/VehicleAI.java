package ca.ubc.ece.cpen221.mp4.ai;

import java.util.LinkedList;

import java.util.Queue;
import java.util.Random;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;



/** 
 * The AI for vehicles.
 * @author erikmaclennan, mkals
 */
public class VehicleAI extends AbstractAI {

    Queue<Direction> directionToGo = new LinkedList<Direction>();
    private Item enemy;
    private int NUM_STEPS_IN_DIRECTION = 10; //steps in one direction for this number of steps.
    private int counter = 0;
    
    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        
        final Set<Item> items = world.searchSurroundings(actor);
        
        if (counter == 0){
            Random generator = new Random();
            int nextDirection = generator.nextInt(4);
            
            if (nextDirection == 0){
                directionToGo.add(Direction.East);
            } else if (nextDirection == 1){
                directionToGo.add(Direction.West);
            } else if (nextDirection == 2){
                directionToGo.add(Direction.North);
            } else if (nextDirection == 3){
                directionToGo.add(Direction.South);
            }
        }
        
        counter++;
        
        if (counter % NUM_STEPS_IN_DIRECTION == 0){
            directionToGo.remove();
            Random generator = new Random();
            int nextDirection = generator.nextInt(4);
            
            if (nextDirection == 0){
                directionToGo.add(Direction.East);
            } else if (nextDirection == 1){
                directionToGo.add(Direction.West);
            } else if (nextDirection == 2){
                directionToGo.add(Direction.North);
            } else if (nextDirection == 3){
                directionToGo.add(Direction.South);
            }
            
        }
        
        
        return makeDecision(directionToGo.peek(), (World) world, actor, items);
        
    }

    /**
     * Determines the command to give the Actor object. If there is an item in the way, 
     * an attack command is issued. Otherwise, a move command in the specified direction is issued.
     * @param direction the direction desired to travel in.
     * @param world the environment our actor is in
     * @param actor the Actor that wants to move.
     * @param items All of the Items around the actor, determined by view range. Includes the actor itself.
     * @return the Command desired depending on the situation.
     */
    private Command makeDecision(Direction direction, World world, Actor actor, Set<Item> items) {
        if (Util.isValidLocation(world, new Location(actor.getLocation(), direction))){
            if (Util.isLocationEmpty((World) world, new Location(actor.getLocation(), direction))) {
                return new MoveCommand(actor, new Location(actor.getLocation(), direction));
            }

            for (Item item : items) {
                if (item.getLocation().equals(new Location(actor.getLocation(), direction))) {
                    enemy = item;
                    break;
                }
            }
            //location isn't empty, so we attack the item in its position.
            
            if (enemy.getStrength() < actor.getStrength()) {
                return new EatCommand(actor, enemy);
            }
            
            
        }
        
        return new WaitCommand();
    }
}
