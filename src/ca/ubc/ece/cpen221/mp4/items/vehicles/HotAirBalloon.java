package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;

public class HotAirBalloon extends Actor {

    { 
        //All item properties
    super.NAME = "Hot Air Balloon";
    super.IMAGE = Util.loadImage("hotairballoon.gif");
    super.IS_MEAT = false;
    super.IS_VEGGIE = false;
    super.MAX_ENERGY = 120;
    super.STRENGTH = 100;
    
    //All Actor properties
    super.INITIAL_ENERGY = 100;
    super.VIEW_RANGE = 15;
    super.COOLDOWN_PERIOD = 3;
    super.MOVING_RANGE = 1;
    }
    
    public HotAirBalloon(AI vehicleAI, Location initialLocation) {
        this.ai = vehicleAI;
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
    }

}
