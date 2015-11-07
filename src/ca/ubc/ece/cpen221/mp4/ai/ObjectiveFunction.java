package ca.ubc.ece.cpen221.mp4.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.AttackCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;

public class ObjectiveFunction {

    // Parameters:

    // negative value makes actor want to escape
    private int repulsionWeight;
    private int foodWeight;
    private int impartialWeight;

    private int attackWeight;
    private double attackDesire;

    private void updateParameters() {

        // for making movement disition
        repulsionWeight = -10;
        foodWeight = ((int) (4 / RELATIVE_ENERGY));
        impartialWeight = -1;

        // for making eating disition
        attackDesire = RELATIVE_ENERGY * attackWeight;

    }

    public ObjectiveFunction(Actor actor) {
        ACTOR = actor;
        RELATIVE_ENERGY = (double) actor.getEnergy() / actor.getMaxEnergy();
        currentLocation = actor.getLocation();
    }

    private Actor ACTOR; // fractional energy left
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

    Command conclusion() {
        
        Vector movementVector = generateMovementVector(currentLocation);
        Set<Direction> potentialVictims = occupiedDirections(edibleLocations);
        
        if (!potentialVictims.isEmpty() && attackDesire > movementVector.movementDesire()) {
            for (Item item : items) {
                if (potentialVictims.contains(item.getLocation())) return new AttackCommand(ACTOR, item);
            }
        }
        
        Set<Direction> occupiedDirections = occupiedDirections(occupiedLocation);
        Direction bestDirection =  movementVector.bestDirectionNotContaining(occupiedDirections);
        return new MoveCommand(ACTOR, new Location(currentLocation, bestDirection));
    }

    Set<Direction> occupiedDirections(Set<Location> locations) {
        
        Set<Direction> victims = new HashSet<>();
        
        if (locations.contains(stepNorth())) victims.add(Direction.North);
        if (locations.contains(stepEast())) victims.add(Direction.East);
        if (locations.contains(stepSouth())) victims.add(Direction.South);
        if (locations.contains(stepWest())) victims.add(Direction.West);    
        
        return victims;
    }

    Location stepNorth() {
        return new Location(currentLocation, Direction.North);
    }

    Location stepEast() {
        return new Location(currentLocation, Direction.East);
    }

    Location stepSouth() {
        return new Location(currentLocation, Direction.South);
    }

    Location stepWest() {
        return new Location(currentLocation, Direction.West);
    }

    Vector generateMovementVector(Location currentLocation) {

        // define vector that sums weighted component vectors of object around
        // it.
        Vector vector = new Vector(currentLocation);

        // attracted to god
        for (Location food : edibleLocations) {
            vector.add(food, foodWeight);
        }

        // repeled from bad
        for (Location bad : preditorLocations) {
            vector.add(bad, repulsionWeight);
        }

        // repeled from occupied location
        for (Location occupied : occupiedLocation) {
            vector.add(occupied, impartialWeight);
        }

        return vector;

    }

    private class Vector {

        private double x;
        private double y;

        private Location origin;

        Vector(Location origin) {
            this.origin = origin;
        }

        void add(Location location, int weight) {

            int xCoordinate = location.getX() - origin.getX();
            int yCoordinate = location.getY() - origin.getY();

            double squaredDistance = xCoordinate ^ 2 + yCoordinate ^ 2;
            double distanceWeight = weight / Math.sqrt(squaredDistance);
            x += xCoordinate * distanceWeight;
            y += yCoordinate * distanceWeight;
        }

        double movementDesire() {
            return Math.sqrt(x * x + y * y);
        }

        Direction bestDirectionNotContaining(Set<Direction> occupiedDirections) {
        
            List<Direction> directions = getPrefferedDirectionsList();
            
            for (Direction direction : directions) {
                if (!occupiedDirections.contains(direction)) return direction;
            }
            
            return null;
        }
        
        private List<Direction> getPrefferedDirectionsList() {
            List<Direction> directions = new ArrayList<>();
            
            //TODO: return a list containin all the direcitons in descending order of preference 
            //(ie, starting with the direction closest to the direction vector)
            
            return directions;
        }
    }
}