package ca.ubc.ece.cpen221.mp4.items.minecraft;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;

/**
 * One of the bosses from Minecraft, developed by Markus Persson.
 * @author erikmaclennan, mkals
 */
public class Wither extends Actor {

    { 
        //All item properties
    super.NAME = "Wither";
    super.IMAGE = Util.loadImage("wither.gif");
    super.IS_MEAT = false;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 10000;
    super.STRENGTH = 450;
    
    //All Actor properties
    super.INITIAL_ENERGY = 8000;
    super.VIEW_RANGE = 10;
    super.COOLDOWN_PERIOD = 1;
    super.MOVING_RANGE = 2;
    }
    
    public Wither(AI witherAI, Location initialLocation) {
        this.ai = witherAI;
        this.location = initialLocation;
        this.energy = this.INITIAL_ENERGY;
    }
    
    @Override
    public Command getNextAction(World world) {
        Command nextAction = this.ai.getNextAction(world, this);
        //cannot die
     //   this.energy = this.energy - 10; // Loses 1000 energy regardless of action.
        return nextAction;
    }
}
