package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Actor;

public class SlowLoris extends ArenaAnimal {
    
    { 
        //All item properties
    super.name = "SlowLoris";
    super.image = Util.loadImage("slowloris.gif");
    super.PLANT_CALORIES = 0;
    super.MEAT_CALORIES = 60;
    super.MAX_ENERGY = 60;
    
        //protected int energy;
    super.STRENGTH = 60;
    
    //All Actor properties
    super.INITIAL_ENERGY = 40;
    super.VIEW_RANGE = 3;

    super.coolDownPeriod = 1;
    super.movingRange = 1;
    
    //ArenaAnimal specific property
    super.MIN_BREEDING_ENERGY = 10;
    
    }

    /**
     * Create a new {@link Rabbit} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param rabbitAI
     *            : The AI designed for rabbits
     * @param initialLocation
     *            : the location where this rabbit will be created
     */
    public SlowLoris(AI SlowLorisAI, Location initialLocation) {
        ai = SlowLorisAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    @Override
    public ArenaAnimal breed() {
        SlowLoris child = new SlowLoris(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }
}

