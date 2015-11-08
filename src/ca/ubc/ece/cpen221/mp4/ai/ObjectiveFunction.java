package ca.ubc.ece.cpen221.mp4.ai;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

public class ObjectiveFunction {

    // Parameters:

    // negative value makes actor want to escape
    private int repulsionWeight;
    private int foodWeight;
    private int impartialWeight;

    private double attackDesire;

    private void updateParameters() {

        // for making movement disition
        repulsionWeight = -10;
        foodWeight = ((int) (4 / RELATIVE_ENERGY));
        impartialWeight = 4;

        // for making eating disition
        attackDesire = RELATIVE_ENERGY * 10;

    }

    public ObjectiveFunction(Actor actor, ArenaWorld world) {
        ACTOR = actor;
        WORLD = world;
        RELATIVE_ENERGY = (double) actor.getEnergy() / actor.getMaxEnergy();
        currentLocation = actor.getLocation();
        updateParameters();
    }

    private Actor ACTOR; // fractional energy left
    private ArenaWorld WORLD;
    private double RELATIVE_ENERGY; // fractional energy left
    private Location currentLocation; // other fields we want to keep track of
                                      // that will impact the objective function

    private Set<Location> occupiedLocation = new HashSet<>();
    private Set<Location> edibleLocations = new HashSet<>();
    private Set<Location> preditorLocations = new HashSet<>();

    private Set<Item> items = new HashSet<>();

    void edible(Item item) {

        edibleLocations.add(item.getLocation());
        occupiedLocation.add(item.getLocation());
        items.add(item);
    }

    void bad(Item item) {

        preditorLocations.add(item.getLocation());
        occupiedLocation.add(item.getLocation());
    }

    void impartial(Item item) {
        occupiedLocation.add(item.getLocation());
    }

    public Command conclusion() {

        Vector movementVector = generateMovementVector(currentLocation);
        Set<Location> attackableLocations = attackableLocations(edibleLocations);
                
        
        if (!attackableLocations.isEmpty() && attackDesire > movementVector.movementDesire()) {
            for (Item item : items) {
                if (attackableLocations.contains(item.getLocation())) {
                    return new AttackCommand(ACTOR, item);
                }
            }
        }

        Set<Direction> occupiedDirections = occupiedDirections(occupiedLocation);
        Direction bestDirection = movementVector.bestDirectionNotContaining(occupiedDirections);
        if (bestDirection == null){
            return new WaitCommand();
        }
        return new MoveCommand(ACTOR, new Location(currentLocation, bestDirection));
    }

    Set<Location> attackableLocations(Set<Location> locations) {
        
        Set<Direction> attackingDirections = occupiedDirections(locations);
        
        Set<Location> attackingLocations = new HashSet<>();
        
        for (Direction attackingDirection : attackingDirections) {
            attackingLocations.add( new Location(currentLocation, attackingDirection) );
        }
        
        return attackingLocations;
    }
    
    Set<Direction> occupiedDirections(Set<Location> locations) {

        Set<Direction> victims = new HashSet<>();

        if (locations.contains(stepNorth()))
            victims.add(Direction.North);
        if (locations.contains(stepEast()))
            victims.add(Direction.East);
        if (locations.contains(stepSouth()))
            victims.add(Direction.South);
        if (locations.contains(stepWest()))
            victims.add(Direction.West);

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
        for (Location occupied : occupiedLocation) {
            vector.add(occupied, impartialWeight);
        }

        return vector;

    }
}
