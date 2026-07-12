package it.unicam.cs.mpgc.rpg129851.PrintLog;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;

public class BattleLogger {
    public static String criticalDamage(Entity attacker, Entity defender){
        return damage(attacker, defender) + " con un COLPO CRITICO!!";
    }
    public static String damage(Entity attacker, Entity defender){
        return attacker.getName() + " di LVL" + attacker.getExperience().getLevel().getActualLevel() + " ha inflitto " + attacker.getAttack().getFinalDamage() + " danni a " + defender.getName();
    }
    public static String death(Entity entity){
        return entity.getName() + " e morto!";
    }
    public static String experienceDrop(Entity entity){
        return entity.getName() + " ha droppato " + entity.getExperience().getStatistic() + " punti esperienza";
    }
    public static String escape() {
        return "Sei scappato dall'orco";
    }
}
