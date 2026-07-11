package it.unicam.cs.mpgc.rpg129851.System;

import it.unicam.cs.mpgc.rpg129851.Model.*;
import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogDeath.*;

public class DeathSystem {

    public boolean deathControl(Entity defender){
        if(!defender.isAlive()){
            printDeath(defender);
            return true;
        }
        return false;
    }
    public boolean deathControl(Entity attacker, Entity defender){
        if(!defender.isAlive()){
            printExperienceDrop(defender);
            attacker.getExperience().earnExperience(defender.getExperience().getStatistic());
            attacker.getHealth().heal(20);
            attacker.getAttack().setAttacking(false);
            return true;
        }
        return false;
    }
}
