package it.unicam.cs.mpgc.rpg129851.Model;
import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogAttack.*;

public class CombatSystem {
    private final DamageCalculator damageCalculator = new DamageCalculator();

    public void executeAttack(Attack attacker, Entity defender) {
        damageCalculator.calculateFinalDamage(attacker, defender);
        defender.getHealth().takeDamage(attacker.getFinalDamage());
    }
    public void attack(Entity attacker, Entity defender){
        if(defender.getHealth().getStatistic() <= 0){

        }else {
            this.executeAttack(attacker.getAttack(), defender);
            printDamage(attacker, defender);
        }
    }
}
