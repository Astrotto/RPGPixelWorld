package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.View.EntityView;
import javafx.geometry.Bounds;

public abstract class Entity {
    private String name;
    private Attack attack = new Attack(new Strength());
    private Defense defense = new Defense();
    private Health health = new Health();
    private Experience experience;
    public EntityView entityView;

    public Entity(String name, int experience, int level) {
        if(name == null || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.experience = new Experience(level, experience, 85);
        }
    }

    public abstract EntityView getEntityView();
    public void setEntityView(EntityView entityView) {
        this.entityView = entityView;
    }
    public abstract Bounds getHitbox(double x, double y);
    public abstract void updateStatistic(int level);

    public String getName() {
        return this.name;
    }
    public boolean isAlive() {
        return this.health.getStatistic() > 0;
    }
    public Attack getAttack(){
        return this.attack;
    }
    public Defense getDefense(){ return this.defense; }

    public Health getHealth() {
        return health;
    }
    public Experience getExperience() {
        return experience;
    }
}
