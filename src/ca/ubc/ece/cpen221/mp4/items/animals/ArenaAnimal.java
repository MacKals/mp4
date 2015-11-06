package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Item;



/**
 * Abstractions for foxes and rabbits that provide additional information that
 * might be of use to the AI:
 * <ol>
 * <li>Maximum energy, given by {@link #getMaxEnergy()}</li>
 * <li>View range, given by {@link #getViewRange()}</li>
 * <li>Minimum breeding energy, given by {@link #getMinimumBreedingEnergy()}
 * </li>
 * </ol>
 */
public abstract class ArenaAnimal extends Actor { 
    

    protected int MIN_BREEDING_ENERGY;

    /**
     * Returns the minimum energy required for an animal to breed
     *
     * @return the minimum breeding energy of this animal
     */
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }

    /**
     * TODO: implement comment
     */
    public void attack(Item enemy) {
        if ((this.MAX_ENERGY - this.energy) > enemy.getEnergy()){
            this.energy += enemy.getEnergy(); 
        }
        else{
            this.energy = this.MAX_ENERGY;
        }
    }

    /**
     * Breeds a child of this {@link ArenaAnimal}, the child must be the same
     * animal as the parent. A {@link ArenaAnimal} can only breed when all of the
     * following conditions are satisfied:
     * <ol>
     * <li>There is an empty location adjacent to its location</li>
     * <li>If it is an {@link ArenaAnimal}, its energy level must exceeds its
     * breeding limit, specified by
     * {@link ArenaAnimal#getMinimumBreedingEnergy()}</li>
     * </ol>
     * After breeding, both the parent and the child should have half of the
     * energy of the parent's energy.
     *
     * @return child the offspring of this animal
     */
    public abstract ArenaAnimal breed();

}
