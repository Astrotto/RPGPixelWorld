package it.unicam.cs.mpgc.rpg129851.Model;

public class Level {
    private static final int MAX_LEVEL = 3;
    private int level;

    public Level(int level){
        this.level = level;
    }
    public int getActualLevel() {
        return level;
    }
    public boolean increaseLevel() {
        if(this.level + 1 <= MAX_LEVEL) {
            this.level++;
            return true;
        }
        return false;
    }
    public int getMaxLevel() {
        return MAX_LEVEL;
    }
}
