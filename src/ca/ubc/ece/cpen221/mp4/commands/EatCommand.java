package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.World;


/**
 * An AttackCommand is a {@link Command} which represents a {@link LivingItem}
 * attacking a {@link enemy}.
 */


public final class EatCommand implements Command {

	private final Actor actor;
	private final Item enemy;

	/**
	 * Construct a {@link AttackCommand}, where <code> item </code> is the eater
	 * and <code> enemy </code> is the enemy. Remember that the enemy must be
	 * adjacent to the eater, and the attacker must have greater strength than the
	 * enemy.
	 *
	 * @param item
	 *            the attacker
	 * @param enemy
	 *            : the enemy
	 */
	public EatCommand(Actor actor, Item enemy) {
		this.actor = actor;
		this.enemy = enemy;
	}
	
	/**
	 * Executes the Attack Command. 
	 * @throws InvalidCommandException when attacker's strength is less than the enemy, 
	 *         or when the enemy is not adjacent
	 * @param World the world that 
	 */
	@Override
	public void execute(World w) throws InvalidCommandException {
		if (actor.getStrength() <= enemy.getStrength())
			throw new InvalidCommandException("Invalid AttackCommand: enemy possesses too much strength");
		if (enemy.getLocation().getDistance(actor.getLocation()) != 1)
			throw new InvalidCommandException("Invalid AttackCommand: enemy is not adjacent");

		actor.attack(enemy);
		enemy.loseEnergy(Integer.MAX_VALUE);
	}
}
