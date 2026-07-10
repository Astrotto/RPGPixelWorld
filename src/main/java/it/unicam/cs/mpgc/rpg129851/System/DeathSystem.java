package it.unicam.cs.mpgc.rpg129851.System;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogDeath.*;

public class DeathSystem {

    public boolean deathControl(Entity defender){
        if(!defender.isAlive()){
            printDeath(defender);
            return true;
        }
        return false;
    }
    public boolean deathOrcControl(Entity attacker, Entity defender){
        if(defender instanceof Orc && !defender.isAlive()){
            printExperienceDrop(defender);
            attacker.getExperience().earnExperience(attacker, defender.getExperience().getStatistic());
            attacker.getHealth().heal(20);
            attacker.getAttack().setAttacking(false);
            return true;
        }
        return false;
    }
}
