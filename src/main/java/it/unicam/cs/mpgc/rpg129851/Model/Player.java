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
            setInitialStatistic(level);
        }
    }
    @Override
    public PlayerView getEntityView() {
        return (PlayerView)this.entityView;
    }
    @Override
    public void setInitialStatistic(int level) {
        switch (level){
            case 1 -> {
                this.getHealth().setBothStatistic(new Health(100, 100));
                this.getAttack().getStrength().setAttribute(25);
                this.getDefense().setAttribute(15);
            }
            case 2 -> {
                this.getHealth().setBothStatistic(new Health(110, 110));
                this.getAttack().getStrength().setAttribute(35);
                this.getDefense().setAttribute(25);
            }
            case 3 -> {
                this.getHealth().setBothStatistic(new Health(125, 125));
                this.getAttack().getStrength().setAttribute(45);
                this.getDefense().setAttribute(35);
            }
        }
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
