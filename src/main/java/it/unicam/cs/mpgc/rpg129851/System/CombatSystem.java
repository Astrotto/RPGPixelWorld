package it.unicam.cs.mpgc.rpg129851.System;

import it.unicam.cs.mpgc.rpg129851.Interface.ActionSystem;
import it.unicam.cs.mpgc.rpg129851.Interface.GameLogger;
import it.unicam.cs.mpgc.rpg129851.Model.Attack;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.PrintLog.PrintGameLog;

import static it.unicam.cs.mpgc.rpg129851.PrintLog.BattleLogger.*;

public class CombatSystem implements ActionSystem {
    private final DamageCalculatorSystem damageCalculator = new DamageCalculatorSystem();
    private final Entity attacker;
    private final Entity defender;
    GameLogger consoleLogger;
    public CombatSystem(Entity attacker, Entity defender) {
        consoleLogger = new PrintGameLog();
        this.attacker = attacker;
        this.defender = defender;
    }
    public boolean execute(){
        if(attacker.isAlive()){
            this.executeAttack(attacker.getAttack(), defender);
            attacker.getAttack().setAttacking(true);
            if(attacker.getAttack().isCriticalHit()) {
                consoleLogger.info(criticalDamage(attacker, defender));
                attacker.getAttack().setCriticalHit(false);
            }else
                consoleLogger.info(damage(attacker, defender));
            return true;
        }
        return false;
    }
    public void executeAttack(Attack attacker, Entity defender) {
        attacker.rollCriticalHit();
        damageCalculator.calculateFinalDamage(attacker, defender);
        defender.getHealth().takeDamage(attacker.getFinalDamage());
    }
}
