package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.Random;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.criticalHit;

public class Attack {
    private Strength strength;
    private int baseDamage;
    private int finalDamage;
    private boolean isAttacking;

    public Attack(int strength){
        this.strength = new Strength(strength);
    }
    public Strength getStrength() {
        return strength;
    }
    public int getBaseDamage() {
        return baseDamage;
    }
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
    public int getFinalDamage() {
        return finalDamage;
    }
    public void setFinalDamage(int finalDamage) {
        this.finalDamage = finalDamage;
    }
    public int attack(Entity target) {
        Random rand = new Random();
        this.setBaseDamage(this.strength.getCurrentStats() + (rand.nextInt(5) - 2));
        this.setFinalDamage(this.getBaseDamage() - target.getDefense().getCurrentStats());
        if(this.getFinalDamage() <= 0) this.setFinalDamage(1);
        if(rand.nextInt(100) < 7) {
            this.setFinalDamage(this.getFinalDamage() * 2);
            criticalHit = true;
        }
        target.getHealth().takeDamage(this.getFinalDamage());
        return this.getFinalDamage();
    }
    public boolean isAttacking(){ return isAttacking; }
    public void setAttacking(boolean attacking){
        isAttacking = attacking;
    }
}
