package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.Random;

public class Attack {
    private Strength strength;
    private int baseDamage;
    private int finalDamage;
    private boolean isAttacking, criticalHit;

    public Attack(Strength strength){ this.strength = strength; }

    public void inflictDamage(Entity target) {
        calculateFinalDamage(target);
        target.getHealth().takeDamage(this.getFinalDamage());
    }
    private void calculateFinalDamage(Entity target){
        Random rand = new Random();
        this.setBaseDamage(this.strength.getAttribute() + (rand.nextInt(5) - 2));
        this.setFinalDamage(this.getBaseDamage() - target.getDefense().getAttribute());
        if(this.getFinalDamage() <= 0) this.setFinalDamage(1);
        if(isCriticalHit())
            this.setFinalDamage(this.getFinalDamage() * 2);
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
    public boolean isCriticalHit(){
        Random rand = new Random();
        if(rand.nextInt(100) < 6)
            setCriticalHit(true);
        return criticalHit;
    }
    public void setCriticalHit(boolean criticalHit){
        this.criticalHit = criticalHit;
    }
    public Strength getStrength() {
        return strength;
    }
    public boolean isAttacking(){ return isAttacking; }
    public void setAttacking(boolean attacking){
        isAttacking = attacking;
    }
}
