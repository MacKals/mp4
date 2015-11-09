package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Random;

import ca.ubc.ece.cpen221.mp4.Direction;

public class Search {

    public enum Goal {
        NE, SE, SW, NW, Centre;
    }
    
    Search() {
        setNewSearchGoal();
    }
    
    private Goal searchingGoal;

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

}
