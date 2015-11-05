package ca.ubc.ece.cpen221.mp4;

import javax.swing.ImageIcon;

public class Item implements ItemInterface{

    protected String name;
    protected Location location;
    protected ImageIcon image = Util.loadImage("unknown.gif");
    
    protected int PLANT_CALORIES;
    protected int MEAT_CALORIES;
    protected int MAX_ENERGY;
    protected int energy;
    protected int STRENGTH;
    
    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }
    
    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energyLoss) {
        energy -= energyLoss;    
    }

    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    @Override
    public int getEnergy() {
        return energy;
    }
    
    @Override
    public int getMeatCalories() {
        return MEAT_CALORIES;
    }

    @Override
    public int getPlantCalories() { 
        return PLANT_CALORIES;
    }
    

}
