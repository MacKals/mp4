package ca.ubc.ece.cpen221.mp4.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Vector {

    private double x;
    private double y;

    private Location origin;
    private ArenaWorld WORLD;

    Vector(Location origin, ArenaWorld world) {
        this.origin = origin;
        WORLD = world;
        
        x = 0.0;
        y = 0.0;
    }

    void add(Location location, int weight) {

        if (location.equals(origin)) return;
        
        int xCoordinate = location.getX() - origin.getX();
        int yCoordinate = location.getY() - origin.getY();
        
        double squaredDistance = (xCoordinate * xCoordinate) + (yCoordinate * yCoordinate);
        double distanceWeight = weight / squaredDistance;
        
        x += xCoordinate * distanceWeight;
        y -= yCoordinate * distanceWeight;
        boolean test = true;
        test = !test;
    }

    //For search machinery
    void add(int XValue, int YValue) {
        x = XValue;
        y = YValue;
    }
    
    double movementDesire() {
        return Math.sqrt(x * x + y * y);
    }

    Direction bestDirectionNotContaining(Set<Direction> occupiedDirections) {

        List<Direction> directions = getPrefferedDirectionsList();
        
        for (Direction direction : directions) {
            if (!occupiedDirections.contains(direction) && Util.isValidLocation(WORLD, new Location(origin, direction)))
                return direction;
        }

        return null;
    }

    private List<Direction> getPrefferedDirectionsList() {
        List<Direction> directions = new ArrayList<>();

        // returns a list containing three directions in descending
        // order of preference
        // (ie, starting with the direction closest to the direction vector)

        if (Math.abs(x) > Math.abs(y)) {
            
            if (x > 0) {
                directions.add(Direction.East);
            } else {
                directions.add(Direction.West);
            }
            
            if (y > 0) {
                directions.add(Direction.North);
                directions.add(Direction.South);
            } else {
                directions.add(Direction.South);
                directions.add(Direction.North);
            }

        } else {
            if (y > 0) {
                directions.add(Direction.North);
            } else {
                directions.add(Direction.South);
            }
            if (x > 0) {
                directions.add(Direction.East);
                directions.add(Direction.West);
            } else {
                directions.add(Direction.West);
                directions.add(Direction.East);
            }
        }

        return directions;
    }
}