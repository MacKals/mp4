package ca.ubc.ece.cpen221.mp4.items.minecraft;

/**
 * A tree from the popular sandbox game, Minecraft, developed by Markus Persson.
 * @author erikmaclennan, mkals
 */
import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Tree extends Item {

    private boolean isDead;
    
    { 
        //All item properties
    super.NAME = "Tree";
    super.IMAGE = Util.loadImage("tree.gif");
    super.IS_MEAT = false;
    super.IS_VEGGIE = true;
    super.MAX_ENERGY = 1000;
    super.STRENGTH = 60;
    
    }
    
    public Tree(Location initialLocation) {
        this.location = initialLocation;
        
        //adjacent locations
        
        this.energy = MAX_ENERGY;
        this.isDead = false;
    }
  
}
