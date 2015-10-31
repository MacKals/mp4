package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Hawk extends Animal {

    private static final int INITIAL_ENERGY = 130;
    private static final int MAX_ENERGY = 150;
    private static final int STRENGTH = 100;
    private static final int VIEW_RANGE = 15; //much vision.
    private static final int MIN_BREEDING_ENERGY = 50;
    private static final int COOLDOWN = 2;
    private static final ImageIcon hawkImage = Util.loadImage("fox.gif"); //create image
    private static boolean isPlant = false;
    
    
}
