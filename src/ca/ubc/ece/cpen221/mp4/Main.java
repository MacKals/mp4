package ca.ubc.ece.cpen221.mp4;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.ai.*;
import ca.ubc.ece.cpen221.mp4.items.Gardener;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.staff.WorldImpl;
import ca.ubc.ece.cpen221.mp4.staff.WorldUI;

/**
 * The Main class initialize a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 *
 * You may modify or add Items/Actors to the World.
 *
 */
public class Main {

	static final int X_DIM = 40;
	static final int Y_DIM = 40;
	
	static final int SPACES_PER_GRASS = 7;
	
	static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
	static final int INITIAL_GNATS = INITIAL_GRASS / 4;
	
	static final int INITIAL_RABBI = INITIAL_GRASS / 4;
	static final int INITIAL_FOXES = INITIAL_GRASS / 32;
	
	static final int INITIAL_HAWKS = INITIAL_GRASS / 32;
	static final int INITIAL_SLOWL = INITIAL_GRASS / 40;
	static final int INITIAL_SNAKE = INITIAL_GRASS / 32;
	
	//not in use
	static final int INITIAL_CARS = INITIAL_GRASS / 100;
	static final int INITIAL_TRUCKS = INITIAL_GRASS / 150;
	static final int INITIAL_MOTORCYCLES = INITIAL_GRASS / 64;
	static final int INITIAL_MANS = INITIAL_GRASS / 150;
	static final int INITIAL_WOMANS = INITIAL_GRASS / 100;
	static final int INITIAL_HUNTERS = INITIAL_GRASS / 150;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().createAndShowWorld();
			}
		});
	}

	World world;
	boolean itemsAreBeingAdded = true;
	
	public void createAndShowWorld() {
		world = new WorldImpl(X_DIM, Y_DIM);
		initializeWorld();
		new WorldUI(world).show();
	}

	public void initializeWorld() {
	    int count = 0;
        
        while (itemsAreBeingAdded) {
            
            itemsAreBeingAdded = false;
            
            if (count < INITIAL_GRASS) add(new Grass(   loc()));
            if (count < INITIAL_GNATS) add(new Gnat(    loc()));
            
            if (count < INITIAL_FOXES) add(new Fox(         new FoxAI(),        loc()));
            if (count < INITIAL_RABBI) add(new Rabbit(      new RabbitAI(),     loc()));
            
            if (count < INITIAL_HAWKS) add(new Hawk(        new RabbitAI(),     loc()));
            if (count < INITIAL_SLOWL) add(new SlowLoris(   new RabbitAI(),     loc()));
            if (count < INITIAL_SNAKE) add(new Snake(       new RabbitAI(),     loc()));
            
            count++;
        }
	}

	private void add(Item item) {
	    
	    itemsAreBeingAdded = true;
	    
	    world.addItem(item);
	    
	    if (item instanceof Actor) {
	        world.addActor((Actor) item);
	    }
	}
	
	private Location loc() {
        return Util.getRandomEmptyLocation(world);
	}
	
}