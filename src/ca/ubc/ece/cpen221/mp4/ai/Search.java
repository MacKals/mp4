package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Random;

public class Search {

    /**
     * four different search goals for the Objective Function to move towards when no 
     * good moves are available
     * @author mKals
     *
     */
    public enum Goal {
        NE, SE, SW, NW, Centre;
    }
    
    /**
     * Initializes instance with a randomized search goal
     */
    Search() {
        setNewSearchGoal();
    }
    
    private Goal searchingGoal;

    /**
     * generates a random search goal for Actor to go towards
     */
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

    /**
     * 
     * @return currently active search goal
     */
    public Goal getSearchGoal() {
        return searchingGoal;
    }

}
