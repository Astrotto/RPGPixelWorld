package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.Random;

import static it.unicam.cs.mpgc.rpg129851.Model.DamageCalculator.*;

public class Attack {
    private Attribute strength;
    private int baseDamage;
    private int finalDamage;
    private boolean isAttacking, criticalHit;
    private Random rand = new Random();

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
    public boolean isCriticalHit(){
        if(rand.nextInt(100) < 6)
            setCriticalHit(true);
        return criticalHit;
    }
    public void setCriticalHit(boolean criticalHit){
        this.criticalHit = criticalHit;
    }
    public Attribute getStrength() {
        return strength;
    }
    public boolean isAttacking(){ return isAttacking; }
    public void setAttacking(boolean attacking){
        isAttacking = attacking;
    }
}
