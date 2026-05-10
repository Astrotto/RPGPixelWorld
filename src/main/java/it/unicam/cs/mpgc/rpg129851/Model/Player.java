package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Player extends Entity implements EntityHitbox {
    int level;
    int experience;
    double speed;
    public Player(int level, int experience, double speed) {
        super("Player", 100, 100, 25, 15);
        if(level < 1 || experience < 0 || speed == 0) {
            throw new IllegalArgumentException("Player invalid");
        }else {
            this.level = level;
            this.experience = experience;
            this.speed = speed;
        }
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 15, y + 21, 14, 20);
    }
    public double getSpeed() {
        return speed;
    }
}
