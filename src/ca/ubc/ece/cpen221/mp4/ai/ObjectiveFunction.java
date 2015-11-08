package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.AttackCommand;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class ObjectiveFunction {

    // Parameters:

    // negative value makes actor want to escape
    private int repulsionWeight;
    private int foodWeight;
    private int impartialWeight;

    private double attackDesire;

    private double MINIMUM_DESIRE_FOR_VECTOR_TO_TAKE_EFFECT = .3;
    
    private void updateParameters() {

        // for making movement disition
        repulsionWeight = -5;
        foodWeight = ((int) (10 / RELATIVE_ENERGY));
        impartialWeight = -1;

        // for making eating disition
        attackDesire = 1000 / RELATIVE_ENERGY;
    }

    public ObjectiveFunction(Actor actor, AbstractAI actorAI, ArenaWorld world) {
        ACTOR_AI = actorAI;
        ACTOR = actor;
        WORLD = world;
        RELATIVE_ENERGY = (double) actor.getEnergy() / actor.getMaxEnergy();
        currentLocation = actor.getLocation();
        updateParameters();
    }

    private Actor ACTOR; // fractional energy left
    private AbstractAI ACTOR_AI; 
    private ArenaWorld WORLD;
    private double RELATIVE_ENERGY; // fractional energy left
    private Location currentLocation; // other fields we want to keep track of
                                      // that will impact the objective function

    private Set<Location> occupiedLocations = new HashSet<>();
    private Set<Location> edibleLocations = new HashSet<>();
    private Set<Location> preditorLocations = new HashSet<>();

    private Set<Item> items = new HashSet<>();

    void edible(Item item) {

        edibleLocations.add(item.getLocation());
        occupiedLocations.add(item.getLocation());
        items.add(item);
    }

    void bad(Item item) {

        preditorLocations.add(item.getLocation());
        occupiedLocations.add(item.getLocation());
    }

    void impartial(Item item) {
        occupiedLocations.add(item.getLocation());
    }

    public Command conclusion() {
        
        Set<Direction> occupiedDirections = occupiedDirections(occupiedLocations);
        
        if (ACTOR instanceof ArenaAnimal) {
            if (RELATIVE_ENERGY > 0.7 && occupiedDirections.size() < 4) {
                
                Location newLocation = Util.getRandomEmptyAdjacentLocation((World) WORLD, currentLocation);
                
                return new BreedCommand((ArenaAnimal) ACTOR, newLocation);
            }   
        }
        
        Vector movementVector = generateMovementVector(currentLocation);
        Set<Location> attackableLocations = attackableLocations(edibleLocations);
        
        
        if (!attackableLocations.isEmpty() && attackDesire > movementVector.movementDesire()) {
            for (Item item : items) {
                if (attackableLocations.contains(item.getLocation())) {
                    return new AttackCommand(ACTOR, item);
                }
            }
        }
        
        Direction bestDirection;
        
       /* if (movementVector.movementDesire() < MINIMUM_DESIRE_FOR_VECTOR_TO_TAKE_EFFECT) {

            Vector searchVector = new Vector(currentLocation, WORLD);

            Goal searchGoal = ACTOR_AI.getSearchGoal();
            
            switch (searchGoal) {
            case NE:
                searchVector.add(WORLD.getWidth(), 0);
            case SE:
                searchVector.add(WORLD.getWidth(), WORLD.getHeight());
            case SW: 
                searchVector.add(0, WORLD.getHeight());
            case NW:
                searchVector.add(0, 0);
            case Centre:
                searchVector.add(WORLD.getWidth() / 2 , WORLD.getHeight() / 2);
            }
            
            bestDirection = searchVector.bestDirectionNotContaining(occupiedDirections);
                    
        } else {*/
            
            bestDirection = movementVector.bestDirectionNotContaining(occupiedDirections);
           
     //   }

        if (bestDirection == null) {
            return new WaitCommand();
        }
        
        return new MoveCommand(ACTOR, new Location(currentLocation, bestDirection));
    }

    
    private Set<Location> attackableLocations(Set<Location> locations) {
        
        Set<Location> attackingLocations = new HashSet<>();
        
        for (Direction attackingDirection : occupiedDirections(locations)) {
            attackingLocations.add( new Location(currentLocation, attackingDirection) );
        }
        
        return attackingLocations;
    }
    
    
    private Set<Direction> occupiedDirections(Set<Location> locations) {

        Set<Direction> victims = new HashSet<>();

        if (locations.contains(stepNorth()))  victims.add(Direction.North);
        if (locations.contains(stepEast()))   victims.add(Direction.East);
        if (locations.contains(stepSouth()))  victims.add(Direction.South);
        if (locations.contains(stepWest()))   victims.add(Direction.West);
        
        return victims;
    }
    
    
    private Location stepNorth() {
        return new Location(currentLocation, Direction.North);
    }
    
    private Location stepEast() {
        return new Location(currentLocation, Direction.East);
    }
    
    private Location stepSouth() {
        return new Location(currentLocation, Direction.South);
    }
    
    private Location stepWest() {
        return new Location(currentLocation, Direction.West);
    }
    
    
    private Vector generateMovementVector(Location currentLocation) {

        // define vector that sums weighted component vectors of object around
        // it.
        Vector vector = new Vector(currentLocation, WORLD);
        
        // attracted to food
        for (Location food : edibleLocations) {
            vector.add(food, foodWeight);
        }

        // repelled from bad
        for (Location bad : preditorLocations) {
            vector.add(bad, repulsionWeight);
        }

        // repelled from occupied location
        for (Location occupied : occupiedLocations) {
            vector.add(occupied, impartialWeight);
        }
        
        return vector;
    }
}
