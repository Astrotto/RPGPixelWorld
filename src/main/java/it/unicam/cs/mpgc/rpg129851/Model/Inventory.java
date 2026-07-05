package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Potion> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void addPotion(Potion potion) {
        if(potion == null){
            throw new NullPointerException("Potion is null");
        }else{
            inventory.add(potion);
        }
    }
    public void removePotion(int level) {
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getLevel() == level) {
                inventory.remove(i);
                break;
            }
        }
    }
    public int getPotionAmount(int level) {
        int counter = 0;
        for(Potion potion : inventory) {
            if(potion.getLevel() == level)
                counter++;
        }
        return counter;
    }
}
