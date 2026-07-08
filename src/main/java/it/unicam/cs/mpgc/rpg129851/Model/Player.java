package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;


public class Player extends Entity implements EntityHitbox {
    private final double speed;
    private Inventory inventory;

    public Player(int level, int experience, double speed) {
        super("Player" , experience, level);
        if(speed == 0 || level <= 0 || level > 3) {
            throw new IllegalArgumentException("Player invalid");
        }else {
            this.speed = speed;
            this.inventory = new Inventory();
            updateStats(level);
        }
    }
    @Override
    public void updateStats(int level) {
        switch (level){
            case 1 -> setStats(100, 100, 25, 15);
            case 2 -> setStats(110, 110, 35, 25);
            case 3 -> setStats(125, 125, 45, 35);
        }
    }
    public Inventory getInventory() {
        return inventory;
    }

    public double getSpeed() {
        return speed;
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 15, y + 21, 14, 20);
    }
}
