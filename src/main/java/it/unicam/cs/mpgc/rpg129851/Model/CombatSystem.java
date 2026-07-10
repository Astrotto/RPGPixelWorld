package it.unicam.cs.mpgc.rpg129851.Model;
import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogAttack.*;

public class CombatSystem {
    private final DamageCalculator damageCalculator = new DamageCalculator();


    public void attack(Entity attacker, Entity defender){
        this.executeAttack(attacker.getAttack(), defender);
        attacker.getAttack().setAttacking(true);
        printDamage(attacker, defender);
    }
    public void executeAttack(Attack attacker, Entity defender) {
        damageCalculator.calculateFinalDamage(attacker, defender);
        defender.getHealth().takeDamage(attacker.getFinalDamage());
    }
}
