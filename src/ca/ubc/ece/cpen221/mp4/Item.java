package ca.ubc.ece.cpen221.mp4;

import javax.swing.ImageIcon;

public class Item implements ItemInterface{

    private String name;
    protected Location location;
    private ImageIcon image;
    
    protected int energy;
    private int STRENGTH;
    
    
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
    public int getMeatCalories() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getPlantCalories() {
        // TODO Auto-generated method stub
        return 0;
    }
    

}
