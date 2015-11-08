package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

public abstract class AbstractAI implements AI {

    AbstractAI() {
        setNewSearchGoal();
    }
    
    protected Goal searchingGoal;

    public void setNewSearchGoal() {

        Random generator = new Random();

        switch (generator.nextInt(5)) {
        case 0:
            searchingGoal = Goal.NE;
            break;
        case 1:
            searchingGoal = Goal.SE;
            break;
        case 2:
            searchingGoal = Goal.SW;
            break;
        case 3:
            searchingGoal = Goal.NW;
            break;
        case 4:
            searchingGoal = Goal.Centre;
            break;
        }
    }

    public Goal getSearchGoal() {
        return searchingGoal;
    }

    public Direction oppositeDir(Direction dir) {
        // returns opposite direction of direction dir

        if (dir == Direction.East)
            return Direction.West;
        else if (dir == Direction.West)
            return Direction.East;
        else if (dir == Direction.South)
            return Direction.North;
        else
            return Direction.South;

    }

    public boolean isLocationEmpty(ArenaWorld world, ArenaAnimal animal, Location location) {
        // returns true if location is empty

        if (!Util.isValidLocation(world, location)) {
            return false;
        }

        Set<Item> possibleMoves = world.searchSurroundings(animal);
        Iterator<Item> it = possibleMoves.iterator();

        while (it.hasNext()) {
            Item item = it.next();
            if (item.getLocation().equals(location)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Command getNextAction(ArenaWorld world, Actor actor) {
        return new WaitCommand();
    }
}
