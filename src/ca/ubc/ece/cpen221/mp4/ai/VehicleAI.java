package ca.ubc.ece.cpen221.mp4.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import ca.ubc.ece.cpen221.mp4.commands.AttackCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.SlowLoris;
import ca.ubc.ece.cpen221.mp4.items.minecraft.Wither;


public class VehicleAI extends AbstractAI {

    Queue<Direction> directionToGo = new LinkedList<Direction>();
    private Item enemy;
    private int NUM_STEPS_IN_DIRECTION = 10;
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

    private Command makeDecision(Direction direction, World world, Actor actor, Set<Item> items) {
        if (Util.isValidLocation(world, new Location(actor.getLocation(), Direction.East))){
            if (Util.isLocationEmpty((World) world, new Location(actor.getLocation(), direction))) {
                return new MoveCommand(actor, new Location(actor.getLocation(), direction));
            }

            for (Item item : items) {
                if (item.getLocation().equals(new Location(actor.getLocation(), direction))) {
                    enemy = item;
                    break;
                }
            }

            return new AttackCommand(actor, enemy);
        }
        return new WaitCommand();
        
    }
}
