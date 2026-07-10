package it.unicam.cs.mpgc.rpg129851.System;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogDeath.*;

public class DeathSystem {

    public void deathControl(Entity defender){
        if(defender.getHealth().getStatistic() <= 0){
            printDeath(defender);
        }
    }
    public void deathOrcControl(Entity orc){
        if(orc instanceof Orc && orc.getHealth().getStatistic() <= 0){
            printDeath(orc);
            printExperienceDrop(orc);
        }
    }
}
