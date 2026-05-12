package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.geometry.Bounds;
import java.util.Random;

public abstract class Entity {
    String name;
    int hp, maxHp;
    int strength, defense, level, experience;
    public Entity(String name, int level, int maxHp, int hp,  int strength, int defense, int experience) {
        if(name == null || level < 0 || level > 3 || maxHp < hp || hp <= 0 || strength <= 0 || defense <= 0 || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.level = level;
            this.hp = hp;
            this.maxHp = maxHp;
            this.strength = strength;
            this.defense = defense;
            this.experience = experience;
        }
    }
    public Entity(String name, int level, int experience) {
        if(name == null || level < 0 || level > 3 || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.level = level;
            this.experience = experience;
        }
    }
    public abstract Bounds getHitbox(double x, double y);
    public void takeDamage(int damage) {
        this.hp -= damage;
        if(this.hp <= 0) hp = 0;
    }
    public int attack(Entity target) {
        Random rand = new Random();
        int baseDamage = this.getStrength() + (rand.nextInt(5) - 2);
        int finalDamage = baseDamage - target.getDefense();
        if(finalDamage <= 0) finalDamage = 1;
        if(rand.nextInt(100) < 7) {
            finalDamage *= 2;
            Main.criticalHit = true;
        }
        target.takeDamage(finalDamage);
        return finalDamage;
    }
    public abstract void updateStats(int level);
    public void setStats(int maxHp, int hp, int strength, int defense) {
        this.maxHp = maxHp;
        this.hp = hp;
        this.strength = strength;
        this.defense = defense;
    }
    public int getLevel(){return this.level;}
    public int getMaxHp() {
        return this.maxHp;
    }
    public double getHealthPercentage() {
        return (double) getCurrentHp() / getMaxHp();
    }
    public int getCurrentHp() {
        return this.hp;
    }
    public void setHp(int hp) {this.hp = hp;}
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
    public double getExperiencePercentage(){return (double) getExperience() / 100;}
    public boolean isAlive() {
        return this.hp > 0;
    }
}
