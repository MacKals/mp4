package ca.ubc.ece.cpen221.mp4.items.minecraft;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Tree extends Item {

    { 
        //All item properties
    super.NAME = "Tree";
    super.IMAGE = Util.loadImage("tree.gif");
    super.IS_MEAT = false;
    super.IS_VEGGIE = true;
    super.MAX_ENERGY = 1000;
    super.STRENGTH = 60;
    
    }
  
}
