package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.AttackCommand;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.*;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

public class ArenaAnimalAI implements AI {
	private int energy;

	public ArenaAnimalAI(int energy) {
		this.energy = energy;
	}

	public boolean isLocationEmpty(ArenaWorld world, Actor actor, Location location) {
		if (!Util.isValidLocation(world, location)) {
			return false;
		}
		Set<Item> possibleMoves = world.searchSurroundings(actor);
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
		Direction dir = Util.getRandomDirection();
		Location targetLocation = new Location(actor.getLocation(), dir);
		Set<Item> possibleEats = world.searchSurroundings(actor);
		Location current = actor.getLocation();
		Iterator<Item> it = possibleEats.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if ((item.getName().equals("Gnat") || item.getName().equals("Rabbit"))
					&& (current.getDistance(item.getLocation()) == 1)) {
				return new AttackCommand(actor, item); // arena animals eat gnats
														// and rabbits
			}
		}
		if (Util.isValidLocation(world, targetLocation) && this.isLocationEmpty(world, actor, targetLocation)) {
			return new MoveCommand(actor, targetLocation);
		}
		return new WaitCommand();
	}

}
