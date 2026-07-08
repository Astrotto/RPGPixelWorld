package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.Random;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.criticalHit;

public class Attack {
    private Strength strength;
    private boolean isAttacking;

    public Attack(){
        strength = new Strength();
    }
    public Strength getStrength() {
        return strength;
    }
    public int attack(Entity target) {
        Random rand = new Random();
        this.strength.setBaseDamage(this.strength.getCurrentStats() + (rand.nextInt(5) - 2));
        this.strength.setFinalDamage(this.strength.getBaseDamage() - target.getDefense());
        if(this.strength.getFinalDamage() <= 0) this.strength.setFinalDamage(1);
        if(rand.nextInt(100) < 7) {
            this.strength.setFinalDamage(this.strength.getFinalDamage() * 2);
            criticalHit = true;
        }
        target.getHealth().takeDamage(this.strength.getFinalDamage());
        return this.strength.getFinalDamage();
    }
    public boolean isAttacking(){ return isAttacking; }
    public void setAttacking(boolean attacking){
        isAttacking = attacking;
    }
}
