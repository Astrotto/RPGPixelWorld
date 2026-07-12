package it.unicam.cs.mpgc.rpg129851.System;

import it.unicam.cs.mpgc.rpg129851.Interface.ActionSystem;
import it.unicam.cs.mpgc.rpg129851.Model.*;
import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogDeath.*;

public class DeathSystem implements ActionSystem {
    private final Entity attacker;
    private final Entity defender;

    public DeathSystem(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public boolean execute(){
        if(!defender.isAlive()){
            printDeath(defender);
            this.deathControl();
            return true;
        }
        return false;
    }
    public void deathControl(){
        if(!defender.isAlive()){
            printExperienceDrop(defender);
            attacker.getExperience().earnExperience(defender.getExperience().getStatistic());
            attacker.getHealth().heal(20);
            attacker.getAttack().setAttacking(false);
        }
    }
}
