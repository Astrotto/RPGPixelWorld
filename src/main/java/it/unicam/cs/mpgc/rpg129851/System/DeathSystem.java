package it.unicam.cs.mpgc.rpg129851.System;

import it.unicam.cs.mpgc.rpg129851.Interface.*;
import it.unicam.cs.mpgc.rpg129851.Model.*;
import it.unicam.cs.mpgc.rpg129851.PrintLog.PrintGameLog;

import static it.unicam.cs.mpgc.rpg129851.PrintLog.BattleLogger.*;

public class DeathSystem implements ActionSystem {
    private final Entity attacker;
    private final Entity defender;
    GameLogger consoleLogger;

    public DeathSystem(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
        consoleLogger = new PrintGameLog();
    }

    public boolean execute(){
        if(!defender.isAlive()){
            consoleLogger.error(death(defender));
            this.deathControl();
            return true;
        }
        return false;
    }
    public void deathControl(){
        if(!defender.isAlive()){
            consoleLogger.info(experienceDrop(defender));
            attacker.getExperience().earnExperience(defender.getExperience().getStatistic());
            attacker.getHealth().heal(20);
            attacker.getAttack().setAttacking(false);
        }
    }
}
