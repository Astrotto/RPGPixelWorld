package it.unicam.cs.mpgc.rpg129851.Model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.util.Random;

public class Orc extends Entity {
    private static final Random RANDOM = new Random();

    public Orc(int level) {
        super("Orc", 0, level);
        if(level <= 0 || level > this.getExperience().getLevel().getMaxLevel()){
            throw new IllegalArgumentException("Lvl must be between 1 and 3");
        }
        updateStatistic(level, 0);
    }

    @Override
    public void updateStatistic(int level, int experience){
        this.getHealth().setBothStatistic(new Health(70 * level, 70 * level));
        this.getAttack().getStrength().setAttribute(20 * level);
        this.getDefense().setAttribute(18 * level);
        this.getExperience().setStatistic(RANDOM.nextInt(12) + (level * 10));
    }

    @Override
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 17, y + 15, 20, 22);
    }
}
