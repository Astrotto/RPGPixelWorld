package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Player extends Entity implements EntityHitbox {
    private double speed;
    private int maxExperience = 100;
    public Player(int level, int experience, double speed) {
        super("Player", level , 100, 100, 25, 15, experience);
        if(speed == 0) {
            throw new IllegalArgumentException("Player invalid");
        }else {
            this.speed = speed;
        }
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 15, y + 21, 14, 20);
    }

    @Override
    public void updateStats(int level) {
        switch (level){
            case 2:
                setStats(120, 120, 35, 25);
                setExperience(45);
                break;
            case 3:
                setStats(140, 140, 45, 40);
                setExperience(65);
        }
    }

    public double getSpeed() {
        return speed;
    }
    public void earnExperience(int experience) {
        if(this.experience + experience >= maxExperience) {
            if(level < 3) {
                this.level++;
                this.maxExperience = 200;
            }
            this.experience = this.experience + experience - 100;
        }else{
            this.experience += experience;
        }
        if(level == 3) {
            this.experience = maxExperience;
        }
    }
}
