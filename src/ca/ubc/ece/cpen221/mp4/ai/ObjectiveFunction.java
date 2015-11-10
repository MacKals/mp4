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
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

/**
 * AI classes initialize an instance of the ObjectiveFunction, 
 * a disition engine that is capable of taking all visible objects into consideration.
 * 
 * The AI class starts to add visible items to the ObjectiveFunction instance in the appropriate
 * categories. A decition will then be made as to what action to perform based on this information
 * and on a set of weights that may be used to tweak the actors behavior. 
 * @author erikmaclennan, mKals
 */

public class ObjectiveFunction {
    
    // Parameter weights, used to tweak AI behavior:

    // negative value makes actor want to escape
    private int repulsionWeight;
    private int foodWeight;
    private int impartialWeight;

    private double attackDesire;

    private double MINIMUM_DESIRE_FOR_VECTOR_TO_TAKE_EFFECT = 2;
    private double MINIMUM_BREED_ENERGY_PERCENTAGE = 0.9;
    private double SEARCH_CHANGE_THRESHOLD = 5;

    /**
     * Updates the dynamic weight parameters, enabling the desitions made to be influenced by
     * the amount of energy the animal has etc.
     */
    private void updateParameters() {

        // for making movement disition
        repulsionWeight = -10;
        foodWeight = ((int) (10 / RELATIVE_ENERGY));
        impartialWeight = -1;

        // for making eating disition
        attackDesire = 1000 / RELATIVE_ENERGY;
    }

    /**
     * Constructs an instance of the ObjectiveFunction AI
     * @param actor Actor that is making a desition
     * @param searchInstance Instance of Search, stored in AI class to persist between rounds. 
     * Enables long term strategy
     * @param world World in which actor is living
     */
    public ObjectiveFunction(Actor actor, Search searchInstance, ArenaWorld world) {
        SEARCH = searchInstance;
        ACTOR = actor;
        WORLD = world;
        RELATIVE_ENERGY = (double) actor.getEnergy() / actor.getMaxEnergy();
        currentLocation = actor.getLocation();
        updateParameters();
    }

    private Actor ACTOR; // fractional energy left
    private Search SEARCH;
    private ArenaWorld WORLD;
    private double RELATIVE_ENERGY; // fractional energy left
    private Location currentLocation; // other fields we want to keep track of
                                      // that will impact the objective function

    private Set<Location> occupiedLocations = new HashSet<>();
    private Set<Location> edibleLocations = new HashSet<>();
    private Set<Location> preditorLocations = new HashSet<>();

    private Set<Item> items = new HashSet<>();

    /**
     * Add edible items that Actor wants to get close too and attack/gain energy from
     * @param item Edible item 
     */
    void edible(Item item) {

        edibleLocations.add(item.getLocation());
        occupiedLocations.add(item.getLocation());
        items.add(item);
    }

    /**
     * Add enemy items that Actor wants to get away from
     * @param item Enemy item
     */
    void bad(Item item) {

        preditorLocations.add(item.getLocation());
        occupiedLocations.add(item.getLocation());
    }

    /**
     * Other items in the world that Actor does not really interact with 
     * @param item Items towards which Actor is impartial
     */
    void impartial(Item item) {
        occupiedLocations.add(item.getLocation());
    }

    /**
     * Examines data stored in ObjectiveFunction and determines which action the Actor should perform
     * @return the action the actor should perform
     */
    public Command conclusion() {

        // Determining weather to breed
        
        Set<Direction> occupiedDirections = occupiedDirections(occupiedLocations);
        
        if (ACTOR instanceof ArenaAnimal) {
            if (RELATIVE_ENERGY > MINIMUM_BREED_ENERGY_PERCENTAGE && occupiedDirections.size() < 4) {
                
                Location newLocation = Util.getRandomEmptyAdjacentLocation((World) WORLD, currentLocation);

                return new BreedCommand((ArenaAnimal) ACTOR, newLocation);
            }
        }

        // Determining weather to attack/eat and generating movement vector
        // whose magnitude determines

        Vector movementVector = generateMovementVector(currentLocation);
        Set<Location> attackableLocations = attackableLocations(edibleLocations);

        if (!attackableLocations.isEmpty() && attackDesire > movementVector.movementDesire()) {
            for (Item item : items) {
                if (attackableLocations.contains(item.getLocation())) {
                    
                    if (item.getStrength() < ACTOR.getStrength()) {
                        return new EatCommand(ACTOR, item);
                    }
                }
            }
        }

        // Determining weather to move, and in which direction to do so.

        Direction bestDirection;

        if (movementVector.movementDesire() < MINIMUM_DESIRE_FOR_VECTOR_TO_TAKE_EFFECT) {
            // If movementVector has too small a magnitude, adopt search
            // behaviour
            Vector searchVector = new Vector(currentLocation, WORLD);

            Search.Goal searchGoal = SEARCH.getSearchGoal();

            switch (searchGoal) {
            case NE:
                searchVector.add(WORLD.getWidth(), 0);
                break;
            case SE:
                searchVector.add(WORLD.getWidth(), WORLD.getHeight());
                break;
            case SW:
                searchVector.add(0, WORLD.getHeight());
                break;
            case NW:
                searchVector.add(0, 0);
                break;
            case Centre:
                searchVector.add(WORLD.getWidth() / 2, WORLD.getHeight() / 2);
                break;
            }

            if (searchVector.movementDesire() < SEARCH_CHANGE_THRESHOLD) {
                SEARCH.setNewSearchGoal();
            }

            bestDirection = searchVector.bestDirectionNotContaining(occupiedDirections);

        } else {

            bestDirection = movementVector.bestDirectionNotContaining(occupiedDirections);

        }

        // stand still if none of the three most desirable moving directions are
        // free
        if (bestDirection == null) {
            return new WaitCommand();
        }

        return new MoveCommand(ACTOR, new Location(currentLocation, bestDirection));
    }

    /**
     * Returns the locations that are attackable for an actor (the locations
     * that are one step directly north, east, south or west).
     * @param locations potential locations to be attacked
     * @return the subset of locations passed inn that can be attacked
     */
    private Set<Location> attackableLocations(Set<Location> locations) {

        Set<Location> attackingLocations = new HashSet<>();

        for (Direction attackingDirection : occupiedDirections(locations)) {
            attackingLocations.add(new Location(currentLocation, attackingDirection));
        }

        return attackingLocations;
    }

    /**
     * Determines the directions 
     * @param locations
     * @return
     */
    private Set<Direction> occupiedDirections(Set<Location> locations) {

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

    /**
     * returns a location one step north of the user
     * @return location one step north
     */
    private Location stepNorth() {
        return new Location(currentLocation, Direction.North);
    }

    /**
     * return a location one step east of the user location
     * @return location one step east
     */
    private Location stepEast() {
        return new Location(currentLocation, Direction.East);
    }

    /**
     * return a location one step south of the user location
     * @return location one step south
     */
    private Location stepSouth() {
        return new Location(currentLocation, Direction.South);
    }

    /**
     * return a location one step west of the user location
     * @return location one step west
     */
    private Location stepWest() {
        return new Location(currentLocation, Direction.West);
    }

    /**
     * Generates movement vector from data sorted in lists, using the appropriate weights
     * @param currentLocation 
     * @return
     */
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
