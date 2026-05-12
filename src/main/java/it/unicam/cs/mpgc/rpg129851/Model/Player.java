package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Player extends Entity implements EntityHitbox {
    int experience;
    double speed;
    public Player(int level, int experience, double speed) {
        super("Player", level , 100, 100, 25, 15);
        if(experience < 0 || speed == 0) {
            throw new IllegalArgumentException("Player invalid");
        }else {
            this.experience = experience;
            this.speed = speed;
        }
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 15, y + 21, 14, 20);
    }

    @Override
    public void updateStats(int level) {

    }

    public double getSpeed() {
        return speed;
    }
    public void getExperience(int experience) {
        this.experience += experience;
    }
}
