package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import java.util.Random;

public class Orc extends Entity implements EntityHitbox {
    public Orc(int level) {
        super("Orc", 0, level);
        if(level <= 0 || level > 3){
            throw new IllegalArgumentException("Lvl must be between 1 and 3");
        }else{
            setInitialStatistic(level);
        }
    }
    @Override
    public void setInitialStatistic(int level) {
        Random random = new Random();
        switch (level){
            case 1 -> {
                this.getHealth().setStatistic(65);
                this.getHealth().setMaxStatistic(65);
                this.getAttack().getStrength().setAttribute(20);
                this.getDefense().setAttribute(15);
                this.getExperience().setStatistic(random.nextInt(10) + 10);
            }
            case 2 -> {
                this.getHealth().setStatistic(85);
                this.getHealth().setMaxStatistic(85);
                this.getAttack().getStrength().setAttribute(30);
                this.getDefense().setAttribute(30);
                this.getExperience().setStatistic(random.nextInt(10) + 20);
            }
            case 3 -> {
                this.getHealth().setStatistic(110);
                this.getHealth().setMaxStatistic(110);
                this.getAttack().getStrength().setAttribute(40);
                this.getDefense().setAttribute(40);
                this.getExperience().setStatistic(random.nextInt(15) + 30);
            }
        }
    }

    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 17, y + 15, 20, 22);
    }
}
