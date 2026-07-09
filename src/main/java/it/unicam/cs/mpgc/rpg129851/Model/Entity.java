package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.View.EntityView;
import javafx.geometry.Bounds;

public abstract class Entity {
    private String name;
    private Attack attack = new Attack(new Strength());
    private Defense defense = new Defense();
    private Health health = new Health();
    private Experience experience;
    private final EntityView entityView;

    public Entity(String name, int experience, int level) {
        if(name == null || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.entityView = new EntityView();
            this.experience = new Experience(level, experience, 85);
        }
    }
    public EntityView getEntityView() {
        return entityView;
    }
    public abstract Bounds getHitbox(double x, double y);
    public abstract void setInitialStatistic(int level);
    public void updateStatistic() {
        this.getHealth().setBothStatistic(this.getHealth().getStatistic() + 10);
        this.getHealth().setMaxStatistic(this.getHealth().getMaxStatistic() + 10);
        this.getAttack().getStrength().setAttribute(this.getAttack().getStrength().getAttribute() + 10);
        this.getDefense().setAttribute(this.getDefense().getAttribute() + 10);
    }
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
