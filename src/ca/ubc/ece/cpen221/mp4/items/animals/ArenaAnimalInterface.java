package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;

/**
 * Abstractions for foxes and rabbits that provide additional information that
 * might be of use to the AI:
 * <ol>
 * <li>Maximum energy, given by {@link #getMaxEnergy()}</li>
 * <li>View range, given by {@link #getViewRange()}</li>
 * <li>Minimum breeding energy, given by {@link #getMinimumBreedingEnergy()}
 * </li>
 * </ol>
 *
 */
public interface ArenaAnimalInterface {

	/**
	 * Returns the limit of the {@link LivingItem}'s energy. The
	 * {@link LivingItem}'s energy should never exceed this limit.
	 *
	 * @return the energy limit of this animal
	 */
	int getMaxEnergy();

	/**
	 * Returns the range of the animal's vision. The range is measured in
	 * Manhattan Distance, for example, if an animal has view range of 2, then
	 * it can see all valid locations in the rectangle
	 * {(x-2,y-2),(x+2,y-2),(x-2,y+2),(x+2,y+2)}, where (x,y) are the
	 * coordinates of its current location.
	 *
	 * @return the view range of this animal
	 */
	int getViewRange();

	/**
	 * Returns the minimum energy required for an animal to breed
	 *
	 * @return the minimum breeding energy of this animal
	 */
	int getMinimumBreedingEnergy();
    /**
     * Returns the current energy of this living thing. A {@link LivingItem}
     * gains energy by eating and loses energy by performing actions. If its
     * energy level drops below or equal to 0, it dies.
     *
     * @return current energy level
     */
    int getEnergy();

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
    ArenaAnimal breed();
    
    
}
