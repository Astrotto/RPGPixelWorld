package it.unicam.cs.mpgc.rpg129851.Model;

public class Level {
    private static final int MAX_LEVEL = 3;
    private int level;
    private boolean levelUp = false;

    public Level(int level){
        this.level = level;
    }
    public int getActualLevel() {
        return level;
    }
    public boolean increaseLevel() {
        if(this.level + 1 <= MAX_LEVEL) {
            this.level++;
            this.levelUp = true;
            return true;
        }
        return false;
    }
    public void setLevelUp(boolean levelUp) {
        this.levelUp = levelUp;
    }
    public boolean levelUp(){ return this.levelUp; }
    public void setLevel(int level) { this.level = level; }
    public int getMaxLevel() {
        return MAX_LEVEL;
    }
}
