package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Item;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public abstract class ArenaAnimal extends Actor implements ArenaAnimalInterface { 
    
    
    protected int MIN_BREEDING_ENERGY;

    @Override
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }

    @Override
    public void attack(Item enemy) {
        if ((this.MAX_ENERGY - this.energy) > enemy.getEnergy()){
            this.energy += enemy.getEnergy(); 
        }
        else{
            this.energy = this.MAX_ENERGY;
        }
    }

    @Override
    public abstract ArenaAnimal breed();

}
