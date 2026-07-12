package it.unicam.cs.mpgc.rpg129851.Model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Player extends Entity {
    private final double speed;
    private final Inventory inventory;

    public Player(int level, int experience, double speed) {
        super("Player", experience, level);
        if(speed == 0 || level <= 0 || level > this.getExperience().getLevel().getMaxLevel()) {
            throw new IllegalArgumentException("Player invalid");
        }
        this.speed = speed;
        this.inventory = new Inventory();
        updateStatistic(level, experience);
    }

    @Override
    public void updateStatistic(int level, int experience) {
        this.getHealth().setBothStatistic(new Health(100 * level, 100 * level));
        this.getAttack().getStrength().setAttribute(25 * level);
        this.getDefense().setAttribute(15 * level);
        this.getExperience().setBothStatistic(new Statistic(experience, 85 * level));
    }
    public Inventory getInventory() {
        return inventory;
    }
    public double getSpeed() {
        return speed;
    }

    @Override
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 15, y + 21, 14, 20);
    }
}
