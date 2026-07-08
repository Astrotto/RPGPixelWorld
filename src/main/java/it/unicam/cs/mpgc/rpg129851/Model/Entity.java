package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.View.EntityView;
import javafx.geometry.Bounds;

public abstract class Entity {
    private String name;
    private Attack attack;
    private Defense defense;
    private Health health;
    private Experience experience;
    private final EntityView entityView;

    public Entity(String name, int experience, int level) {
        if(name == null || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.entityView = new EntityView();
            this.experience = new Experience(level, experience, 100);
        }
    }
    public EntityView getEntityView() {
        return entityView;
    }
    public abstract Bounds getHitbox(double x, double y);
    public abstract void updateStats(int level);

    public void setStats(int maxHp, int hp, int strength, int defense) {
        this.health = new Health(hp, maxHp);
        this.attack = new Attack(strength);
        this.defense = new Defense(defense);
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
