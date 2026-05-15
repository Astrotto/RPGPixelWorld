package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Player extends Entity implements EntityHitbox {
    private double speed;
    private int maxExperience = 100;
    public Player(int level, int experience, double speed) {
        super("Player" , experience);
        if(speed == 0 || level <= 0 || level > 3) {
            throw new IllegalArgumentException("Player invalid");
        }else {
            this.speed = speed;
            this.level = level;
            updateStats(level);
        }
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 15, y + 21, 14, 20);
    }

    @Override
    public void updateStats(int level) {
        switch (level){
            case 1:
                setStats(100, 100, 25, 15);
                break;
            case 2:
                setStats(110, 110, 35, 25);
                break;
            case 3:
                setStats(125, 125, 45, 40);
        }
    }

    public double getSpeed() {
        return speed;
    }
    public void earnExperience(int experience) {
        if(this.experience + experience >= maxExperience) {
            if(level < 3) {
                this.level++;
                updateStats(this.level);
                this.maxExperience = 200;
            }
            this.experience = this.experience + experience - this.maxHp;
        }else{
            this.experience += experience;
        }
        if(level == 3) {
            this.experience = maxExperience;
        }
    }
}
