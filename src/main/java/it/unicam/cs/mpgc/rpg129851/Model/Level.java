package it.unicam.cs.mpgc.rpg129851.Model;

public class Level {
    private int level;

    public Level(int level){
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public boolean increaseLevel() {
        if(this.getLevel() + 1 <= 3) {
            this.level++;
            return true;
        }else return false;
    }
}
