package it.unicam.cs.mpgc.rpg129851.Model;

import javafx.geometry.Bounds;
import java.util.Random;

public abstract class Entity {
    String name;
    int maxHp, hp;
    int strength;
    int defense;
    public Entity(String name, int maxHp, int hp,  int strength, int defense) {
        if(name == null || maxHp <= 0 || strength <= 0 || defense <= 0) {
            throw new IllegalArgumentException("Entity invalid");
        }else{
            this.name = name;
            this.maxHp = maxHp;
            this.hp = hp;
            this.strength = strength;
            this.defense = defense;
        }
    }
    public abstract Bounds getHitbox(double x, double y);
    public void takeDamage(int damage) {
        this.hp -= damage;
        if(this.hp <= 0) hp = 0;
    }
    public void attack(Entity target) {
        Random rand = new Random();
        int baseDamage = this.getStrength() + (rand.nextInt(5) - 2);
        int finalDamage = baseDamage - target.defense;
        if(finalDamage <= 0) finalDamage = 0;
        if(rand.nextInt(100) < 10) {
            finalDamage *= 2;
            System.out.println("COLPO CRITICO!");
        }
        target.takeDamage(finalDamage);
    }
    public double getHealthPercentage() {
        return (double) this.hp / this.maxHp;
    }
    public int getCurrentHp() {
        return hp;
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
