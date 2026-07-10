package it.unicam.cs.mpgc.rpg129851.PrintLog;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;

public class PrintLogDeath {

    public static void printDeath(Entity entity){
        if(entity.getHealth().getStatistic() <= 0){
            System.out.println(entity.getName() + " e morto!");
            if(entity instanceof Orc) {
                System.out.println(entity.getName() + " ha droppato " + entity.getExperience().getStatistic() + " punti esperienza");
            }
        }
    }
}
