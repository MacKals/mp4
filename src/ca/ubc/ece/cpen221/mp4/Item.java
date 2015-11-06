package ca.ubc.ece.cpen221.mp4;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Gnat;


/**
 * An Item represents a physical object in the virtual World that occupies a
 * field and is represented with an image.
 */
public abstract class Item {

    protected String NAME;
    protected ImageIcon IMAGE;
    protected boolean IS_MEAT;
    protected boolean IS_VEGGIE;
    protected int MAX_ENERGY;
    protected int STRENGTH;
    
    protected int energy;
    protected Location location;
    
    
    /**
     * Returns the limit of the {@link Item}'s energy. The
     * {@link Item}'s energy should never exceed this limit.
     *
     * @return the energy limit of this animal
     */
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }
    
    /**
     * The visualization of this Item in the world.
     *
     * @return the image of this Item
     */
    public ImageIcon getImage() {
        return IMAGE;
    }

    /**
     * Gets a String that serves as a unique identifier for this type of Item.
     *
     * @return the name of this item
     */
    public String getName() {
        return NAME;
    }

    /**
     * Gets the location of this Item in the World.
     *
     * @return the location in the world
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the strength of this Item. Generally, if an item possesses
     * greater strength than another, then it can eliminate the other. For
     * example, a {@link Vehicle} can run over everything that has a lower
     * strength.
     *
     * @return the strength of this Item
     */
    public int getStrength() {
        return STRENGTH;
    }

    /**
     * Causes this Item to lose energy. The consequences of this varies for
     * different types of Items.
     * <ul>
     * <li>{@link Grass} and {@link Gnat} die when they lose energy.</li>
     * <li>{@link ArenaAnimal} reduces its energy level and it dies if its
     * energy level drops below or equal to 0</li>
     * </ul>
     *
     * @param energy
     *            the amount of energy lost
     */
    public void loseEnergy(int energyLoss) {
        energy -= energyLoss;    
    }

    /**
     * Returns whether or not this Item is dead. If this Item is dead, it will
     * be removed from the World. An item is dead if it is eaten, run over by a
     * Vehicle, loses all its energy and energy level drops below or equal 0,
     * etc.
     *
     * @return true if this Item is dead, false if alive
     */
    public boolean isDead() {
        return energy <= 0;
    }

    /**
     * Returns the current energy of this living thing. A {@link LivingItem}
     * gains energy by eating and loses energy by performing actions. If its
     * energy level drops below or equal to 0, it dies.
     *
     * @return current energy level
     */
    public int getEnergy() {
        return energy;
    }
    
    /**
     * 
     * @return meat energy of this item
     */
    public int getMeatCalories() {
        
        if (IS_MEAT) return energy;
        return 0;
    }
    
    /**
     * 
     * @return plant energy of this item
     */
    public int getPlantCalories() { 
        
        if (IS_VEGGIE) return energy;
        return 0;
    }
}
