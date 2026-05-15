package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Orc extends Entity implements EntityHitbox {
    public Orc(int level) {
        super("Orc", 0);
        if(level <= 0 || level > 3){
            throw new IllegalArgumentException("Lvl must be between 1 and 3");
        }else{
            this.level = level;
            updateStats(level);
        }
    }
     public void updateStats(int level){
        switch (level){
            case 1:
                setStats(65, 65, 20, 15);
                setExperience(25);
                break;
            case 2:
                setStats(85, 85, 30, 20);
                setExperience(45);
                break;
            case 3:
                setStats(110, 110, 45, 35);
                setExperience(65);
        }
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 17, y + 15, 20, 22);
    }

}
