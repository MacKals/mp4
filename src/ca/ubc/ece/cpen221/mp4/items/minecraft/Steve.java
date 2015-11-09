package ca.ubc.ece.cpen221.mp4.items.minecraft;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;

/**
 * Steve is the moniker given to hero in the popular sandbox game, Minecraft. The game was 
 * developed by Markus Persson in Java.
 * @author erikmaclennan, mkals
 *
 */
public class Steve extends Actor {

    { 
        //All item properties
    super.NAME = "Steve";
    super.IMAGE = Util.loadImage("steve.gif");
    super.IS_MEAT = true;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 600;
    super.STRENGTH = 300; //he can punch trees with his bare hands.
    
    //All Actor properties
    super.INITIAL_ENERGY = 500;
    super.VIEW_RANGE = 10;
    super.COOLDOWN_PERIOD = 1;
    super.MOVING_RANGE = 1;
    
    }
    public Steve(AI steveAI, Location initialLocation) {
        ai = steveAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    
}
