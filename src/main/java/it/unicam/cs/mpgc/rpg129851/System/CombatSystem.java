package it.unicam.cs.mpgc.rpg129851.System;
import it.unicam.cs.mpgc.rpg129851.Model.Attack;
import it.unicam.cs.mpgc.rpg129851.Model.DamageCalculator;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;

import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogAttack.*;

public class CombatSystem {
    private final DamageCalculator damageCalculator = new DamageCalculator();

    public void attack(Entity attacker, Entity defender){
        if(attacker.isAlive()){
            this.executeAttack(attacker.getAttack(), defender);
            attacker.getAttack().setAttacking(true);
            if(attacker.getAttack().isCriticalHit()) {
                printCriticalDamage(attacker, defender);
                attacker.getAttack().setCriticalHit(false);
            }else
                printDamage(attacker, defender);
        }
    }
    public void executeAttack(Attack attacker, Entity defender) {
        damageCalculator.calculateFinalDamage(attacker, defender);
        defender.getHealth().takeDamage(attacker.getFinalDamage());
    }
}
