package it.unicam.cs.mpgc.rpg129851.System;
import it.unicam.cs.mpgc.rpg129851.Interface.ActionSystem;

import java.util.Random;

import static it.unicam.cs.mpgc.rpg129851.PrintLog.PrintLogEscape.printEscape;

public class EscapeSystem implements ActionSystem {
    private final Random random = new Random();
    private int percentageOfEscape;

    public EscapeSystem() {
        this.percentageOfEscape = 25;
    }

    public boolean execute() {
        if(random.nextInt(100) < this.getPercentageOfEscape()){
            printEscape();
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
