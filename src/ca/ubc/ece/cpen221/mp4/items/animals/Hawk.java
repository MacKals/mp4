package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Hawk implements ArenaAnimal {

    private static final int INITIAL_ENERGY = 130;
    private static final int MAX_ENERGY = 150;
    private static final int STRENGTH = 100;
    private static final int VIEW_RANGE = 15; //much vision.
    private static final int MIN_BREEDING_ENERGY = 50;
    private static final int COOLDOWN = 2;
    private static final ImageIcon hawkImage = Util.loadImage("fox.gif"); //create image
    
    private final AI ai;
    
    private Location location;
    private int energy;
    
    /**
     * Create a new {@link Hawk} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param hawkAI
     *            : The AI designed for hawks
     * @param initialLocation
     *            : the location where this hawk will be created
     */
    public Hawk(AI hawkAI, Location initialLocation) {
        ai = hawkAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    
    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public LivingItem breed() {
        Hawk child = new Hawk(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }

    @Override
    public void eat(Food food) {
        // hawks are carnivorous.
        energy = Math.min(MAX_ENERGY, energy + food.getMeatCalories());
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
        
    }

    @Override
    public int getMovingRange() { //The hawk is really fast.
        return 3;
    }

    @Override
    public ImageIcon getImage() {
        return hawkImage;
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
        //Hawks are not plants.
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
