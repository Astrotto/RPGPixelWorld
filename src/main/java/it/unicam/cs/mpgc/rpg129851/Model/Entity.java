package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.geometry.Bounds;
import java.util.Random;

public abstract class Entity {
    String name;
    int hp, maxHp;
    int strength;
    int defense;
    public Entity(String name, int maxHp, int hp,  int strength, int defense) {
        if(name == null || maxHp < hp || hp <= 0 || strength <= 0 || defense <= 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.hp = hp;
            this.maxHp = maxHp;
            this.strength = strength;
            this.defense = defense;
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
        int finalDamage = baseDamage - target.defense;
        if(finalDamage <= 0) finalDamage = 1;
        if(rand.nextInt(100) < 7) {
            finalDamage *= 2;
            Main.criticalHit = true;
        }
        target.takeDamage(finalDamage);
        return finalDamage;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public double getHealthPercentage() {
        return (double) getCurrentHp() / getMaxHp();
    }
    public int getCurrentHp() {
        return hp;
    }
    public String getName() {
        return name;
    }
    public int getStrength() {
        return strength;
    }
    public int getDefense() {
        return defense;
    }
    public boolean isAlive() {
        return hp > 0;
    }
}
