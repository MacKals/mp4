package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Snake implements ArenaAnimal {

    private static final int INITIAL_ENERGY = 20;
    private static final int MAX_ENERGY = 30;
    private static final int STRENGTH = 50;
    private static final int VIEW_RANGE = 10; //much vision.
    private static final int MIN_BREEDING_ENERGY = 10;
    private static final int COOLDOWN = 5;
    private static final ImageIcon snakeImage = Util.loadImage("fox.gif"); //create image
    
    private final AI ai;
    
    private Location location;
    private int energy;
    
    /**
     * Create a new {@link Snake} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param snakeAI
     *            : The AI designed for snakes
     * @param initialLocation
     *            : the location where this snake will be created
     */
    public Snake(AI snakeAI, Location initialLocation) {
        ai = snakeAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }
   
    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public LivingItem breed() {
        Snake child = new Snake(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }

    @Override
    public void eat(Food food) {
        // snakes are carnivorous.
        energy = Math.min(MAX_ENERGY, energy + food.getMeatCalories());
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
        
    }

    @Override
    public int getMovingRange() { 
        return 1;
    }

    @Override
    public ImageIcon getImage() {
        return snakeImage;
    }

    @Override
    public String getName() {
        return "Hawk";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energyLoss) {
        this.energy -= energyLoss;
        
    }

    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    @Override
    public int getPlantCalories() {
        //Snakes are not plants.
        return 0;
    }

    @Override
    public int getMeatCalories() {
        return energy;
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.
        return nextAction;
    }

    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    @Override
    public int getViewRange() {
        return VIEW_RANGE;
    }

    @Override
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }

}
