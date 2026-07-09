package it.unicam.cs.mpgc.rpg129851.Model;

public class CombatSystem {
    private final DamageCalculator damageCalculator = new DamageCalculator();

    public void executeAttack(Attack attacker, Entity defender) {
        damageCalculator.calculateFinalDamage(attacker, defender);
        defender.getHealth().takeDamage(attacker.getFinalDamage());
    }
}
