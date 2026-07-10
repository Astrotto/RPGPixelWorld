package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.View.EntityView;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;


public class Player extends Entity {
    private final double speed;
    private Inventory inventory;

    public Player(int level, int experience, double speed) {
        super("Player" , experience, level);
        if(speed == 0 || level <= 0 || level > 3) {
            throw new IllegalArgumentException("Player invalid");
        }else {
            this.speed = speed;
            this.inventory = new Inventory();
            this.setEntityView(new PlayerView());
            updateStatistic(level);
        }
    }
    @Override
    public PlayerView getEntityView() {
        return (PlayerView)this.entityView;
    }

    public void updateStatistic(int level){
        this.getHealth().setBothStatistic(new Health(100 * level, 100 * level));
        this.getAttack().getStrength().setAttribute(25 * level);
        this.getDefense().setAttribute(15 * level);
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
