package it.unicam.cs.mpgc.rpg129851.PrintLog;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;

public class PrintLogAttack {

    public static void printDamage(Entity attacker, Entity defender){
        String criticalHit = " con un COLPO CRITICO!!";
        String log = attacker.getName() + " di LVL" + attacker.getExperience().getLevel() + " ha inflitto " + attacker.getAttack().getFinalDamage() + " danni a " + defender.getName();
        if (attacker.getAttack().isCriticalHit()) {
            System.out.println(log + criticalHit);
            attacker.getAttack().setCriticalHit(false);
        }else {
            System.out.println(log);
        }
    }
}
