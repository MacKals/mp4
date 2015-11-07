package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;

public class Marauder extends Actor {

    { 
        //All item properties
    super.NAME = "Marauder";
    super.IMAGE = Util.loadImage("marauder.gif");
    super.IS_MEAT = false;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 1000;
    super.STRENGTH = 5000;
    
    //All Actor properties
    super.INITIAL_ENERGY = 1000;
    super.VIEW_RANGE = 15;
    super.COOLDOWN_PERIOD = 3;
    super.MOVING_RANGE = 1;
    }
    
    public Marauder(AI vehicleAI, Location initialLocation) {
        this.ai = vehicleAI;
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
    }
   
}
