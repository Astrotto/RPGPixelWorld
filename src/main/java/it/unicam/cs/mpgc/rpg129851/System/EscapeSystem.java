package it.unicam.cs.mpgc.rpg129851.System;
import it.unicam.cs.mpgc.rpg129851.Interface.GameLogger;
import it.unicam.cs.mpgc.rpg129851.PrintLog.PrintGameLog;
import java.util.Random;
import static it.unicam.cs.mpgc.rpg129851.PrintLog.BattleLogger.escape;

public class EscapeSystem {
    private final Random random = new Random();
    private int percentageOfEscape;
    GameLogger consoleLogger;

    public EscapeSystem() {
        consoleLogger = new PrintGameLog();
        this.percentageOfEscape = 25;
    }

    public boolean tryEscape() {
        if(random.nextInt(100) < this.getPercentageOfEscape()){
            consoleLogger.info(escape());
            this.resetPercentageOfEscape();
            return true;
        }else{
            this.increasePercentageOfEscape();
        }
        return false;
    }
    public int getPercentageOfEscape() {
        return percentageOfEscape;
    }
    public void resetPercentageOfEscape() {
        this.percentageOfEscape = 25;
    }
    public void increasePercentageOfEscape() {
        this.percentageOfEscape += this.percentageOfEscape * 60 / 100;
    }


}
