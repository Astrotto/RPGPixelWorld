package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Orc extends Entity implements EntityHitbox {
    public Orc(int level) {
        super("Orc", 0, level);
        if(level <= 0 || level > 3){
            throw new IllegalArgumentException("Lvl must be between 1 and 3");
        }else{
            updateStats(level);
        }
    }

     public void updateStats(int level){
        Random random = new Random();
        switch (level){
            case 1:
                setStats(65, 65, 20, 15);
                this.getExperience().setCurrentStats(random.nextInt(10) + 10);
                break;
            case 2:
                setStats(85, 85, 30, 30);
                this.getExperience().setCurrentStats(random.nextInt(10) + 20);
                break;
            case 3:
                setStats(110, 110, 40, 40);
                this.getExperience().setCurrentStats(random.nextInt(15) + 30);
        }
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 17, y + 15, 20, 22);
    }
}
