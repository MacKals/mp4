package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.SlowLorisAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Actor;

/**
 * The Slow Loris is a nocturnal animal from Asia. They live in trees, and are omnivorous.
 * @author erikmaclennan, mkals
 */
public class SlowLoris extends ArenaAnimal {
    
    { 
        //All item properties
    super.NAME = "Slow Loris";
    super.IMAGE = Util.loadImage("slowloris.gif");
    super.IS_MEAT = true;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 120;
    super.STRENGTH = 100;
    
    //All Actor properties
    super.INITIAL_ENERGY = 100;
    super.VIEW_RANGE = 5;
    super.COOLDOWN_PERIOD = 3;
    super.MOVING_RANGE = 1;
    
    //ArenaAnimal specific property
    super.MIN_BREEDING_ENERGY = 20;
    
    }

    /**
     * Create a new {@link SlowLoris} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param SlowLorisAI
     *            : The AI designed for slow lorises
     * @param initialLocation
     *            : the location where this slow lorises will be created
     */
    public SlowLoris(AI SlowLorisAI, Location initialLocation) {
        ai = SlowLorisAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    @Override
    public ArenaAnimal breed() {
        SlowLoris child = new SlowLoris(new SlowLorisAI(), location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }
}

