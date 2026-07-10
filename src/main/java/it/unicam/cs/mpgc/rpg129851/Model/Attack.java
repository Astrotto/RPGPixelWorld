package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.Random;

public class Attack {
    private final Attribute strength;
    private final Random rand = new Random();
    private int baseDamage;
    private int finalDamage;
    private boolean isAttacking;
    private boolean criticalHit;

    public Attack(Attribute strength){ this.strength = strength; }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
    public void setFinalDamage(int finalDamage) {
        this.finalDamage = finalDamage;
    }
    public int getBaseDamage() {
        return baseDamage;
    }
    public int getFinalDamage() {
        return finalDamage;
    }
    public void rollCriticalHit() {
        this.criticalHit = rand.nextInt(100) < 6;
    }
    public boolean isCriticalHit() {
        return criticalHit;
    }
    public void setCriticalHit(boolean criticalHit) {
        this.criticalHit = criticalHit;
    }
    public Attribute getStrength() {
        return strength;
    }
    public boolean isAttacking() {
        return isAttacking;
    }
    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
}
