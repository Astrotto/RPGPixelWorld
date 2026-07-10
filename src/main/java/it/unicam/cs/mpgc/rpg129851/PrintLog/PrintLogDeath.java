package it.unicam.cs.mpgc.rpg129851.PrintLog;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;

public class PrintLogDeath {

    public static void printDeath(Entity entity){
        PrintGameLog.info(entity.getName() + " e morto!");
    }
    public static void printExperienceDrop(Entity entity){
        PrintGameLog.info(entity.getName() + " ha droppato " + entity.getExperience().getStatistic() + " punti esperienza");
    }
}
