package it.unicam.cs.mpgc.rpg129851.Model;

import java.util.Random;

public class DamageCalculator {
    private Random rand = new Random();


    public void calculateFinalDamage(Attack attacker, Entity defender){
        attacker.setBaseDamage(attacker.getStrength().getAttribute() + (rand.nextInt(5) - 2));

        attacker.setFinalDamage(attacker.getBaseDamage() - defender.getDefense().getAttribute());

        if(attacker.getFinalDamage() <= 0) attacker.setFinalDamage(1);

        if(attacker.isCriticalHit())
            attacker.setFinalDamage(attacker.getFinalDamage() * 2);
    }
}
