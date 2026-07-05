package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

import java.util.Random;

public abstract class Entity {
    private String name;
    int strength, defense, level, experience;
    public int maxExperience = 100;
    private long lastChangeFrame;
    private int actualFrame;
    private boolean isAttacking;
    private Health health;
    private ImageView entityView;

    public Entity(String name, int experience) {
        if(name == null || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.experience = experience;
            this.actualFrame = 0;
            this.lastChangeFrame = 0;
            this.health = new Health();
        }
    }
    public ImageView getEntityView() {
        return this.entityView;
    }
    public void setEntityView(ImageView entityView) {
        this.entityView = entityView;
    }
    public abstract Bounds getHitbox(double x, double y);

    public int attack(Entity target) {
        Random rand = new Random();
        int baseDamage = this.getStrength() + (rand.nextInt(5) - 2);
        int finalDamage = baseDamage - target.getDefense();
        if(finalDamage <= 0) finalDamage = 1;
        if(rand.nextInt(100) < 7) {
            finalDamage *= 2;
            Main.criticalHit = true;
        }
        target.health.takeDamage(finalDamage);
        return finalDamage;
    }
    public abstract void updateStats(int level);
    public void setStats(int maxHp, int hp, int strength, int defense) {
        this.health.setCurrentHealth(hp);
        this.health.setMaxHealth(maxHp);
        setStrength(strength);
        setDefense(defense);
    }
    public int getLevel(){return this.level;}
    public String getName() {
        return this.name;
    }
    public int getStrength() {
        return this.strength;
    }
    public void setStrength(int strength) {this.strength = strength;}
    public int getDefense() {
        return this.defense;
    }
    public void setDefense(int defense) {this.defense = defense;}
    public int getExperience() {return this.experience;}
    public void setExperience(int experience) {this.experience = experience;}
    public double getExperiencePercentage(){return (double) getExperience() / this.getMaxExp();}
    public boolean isAlive() {
        return this.health.getCurrentHealth() > 0;
    }
    public int getMaxExp(){ return maxExperience; }

    public boolean isAttacking(){
        return isAttacking;
    }
    public void setAttacking(boolean attacking){
        isAttacking = attacking;
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
}
