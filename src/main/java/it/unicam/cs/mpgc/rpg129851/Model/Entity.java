package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.View.EntityView;
import it.unicam.cs.mpgc.rpg129851.View.LevelView;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

import java.util.Random;

public abstract class Entity {
    private String name;
    int defense;
    private long lastChangeFrame;
    private int actualFrame;
    private boolean isAttacking;
    private final Attack attack;
    private final Health health;
    private final Experience experience;
    private final EntityView entityView;

    public Entity(String name, int experience, int level) {
        if(name == null || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.actualFrame = 0;
            this.lastChangeFrame = 0;
            this.attack = new Attack();
            this.health = new Health();
            this.experience = new Experience(level, experience);
            this.entityView = new EntityView();
        }
    }
    public EntityView getEntityView() {
        return entityView;
    }
    public abstract Bounds getHitbox(double x, double y);


    public abstract void updateStats(int level);
    public void setStats(int maxHp, int hp, int strength, int defense) {
        this.health.setCurrentStats(hp);
        this.health.setMaxStats(maxHp);
        this.attack.getStrength().setCurrentStats(strength);
        setDefense(defense);
    }
    public String getName() {
        return this.name;
    }
    public int getDefense() {
        return this.defense;
    }
    public void setDefense(int defense) {this.defense = defense;}
    public boolean isAlive() {
        return this.health.getCurrentStats() > 0;
    }
    public Attack getAttack(){
        return this.attack;
    }

    public long getLastChangeFrame() {
        return lastChangeFrame;
    }
    public void setLastChangeFrame(long  lastChangeFrame) {
        this.lastChangeFrame = lastChangeFrame;
    }
    public int getActualFrame() {
        return actualFrame;
    }
    public void setActualFrame(int actualFrame) {
        this.actualFrame = actualFrame;
    }
    public Health getHealth() {
        return health;
    }
    public Experience getExperience() {
        return experience;
    }
}
