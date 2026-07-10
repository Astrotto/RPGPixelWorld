package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.View.EntityView;
import it.unicam.cs.mpgc.rpg129851.View.OrcView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import java.util.Random;

public class Orc extends Entity {
    public Orc(int level) {
        super("Orc", 0, level);
        if(level <= 0 || level > 3){
            throw new IllegalArgumentException("Lvl must be between 1 and 3");
        }else{
            this.setEntityView(new OrcView());
            updateStatistic(level);
        }
    }
    @Override
    public OrcView getEntityView(){
        return (OrcView)this.entityView;
    }
    @Override
    public void updateStatistic(int level){
        Random random = new Random();
        this.getHealth().setBothStatistic(new Health(70 * level, 70 * level));
        this.getAttack().getStrength().setAttribute(20 * level);
        this.getDefense().setAttribute(18 * level);
        this.getExperience().setStatistic(random.nextInt(12) + (level * 10));
    }


    @Override
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 17, y + 15, 20, 22);
    }
}
