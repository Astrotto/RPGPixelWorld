package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.Random;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.criticalHit;

public class Attack {
    private Strength strength;
    private int baseDamage;
    private int finalDamage;
    private boolean isAttacking;

    public Attack(Strength strength){ this.strength = strength; }

    public int attack(Entity target) {
        Random rand = new Random();
        this.setBaseDamage(this.strength.getAttribute() + (rand.nextInt(5) - 2));
        this.setFinalDamage(this.getBaseDamage() - target.getDefense().getAttribute());
        if(this.getFinalDamage() <= 0) this.setFinalDamage(1);
        if(rand.nextInt(100) < 6) {
            this.setFinalDamage(this.getFinalDamage() * 2);
            criticalHit = true;
        }
        target.getHealth().takeDamage(this.getFinalDamage());
        return this.getFinalDamage();
    }
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
    public Strength getStrength() {
        return strength;
    }
    public boolean isAttacking(){ return isAttacking; }
    public void setAttacking(boolean attacking){
        isAttacking = attacking;
    }
}
