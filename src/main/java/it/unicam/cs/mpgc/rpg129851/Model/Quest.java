package it.unicam.cs.mpgc.rpg129851.Model;

public class Quest {
    public String task;
    public int howMuch;
    public String who;
    public int level;
    public int potionRewardLevel;
    public Quest(){}

    public String getTask(){return task;}
    public String getWho(){
        return who;
    }
    public int getHowMuch(){
        return howMuch;
    }
    public int getLevel(){
        return level;
    }
    public int getPotionRewardLevel(){
        return potionRewardLevel;
    }
    public String toString(){
        return "La quest e': " + this.getTask() + " " + this.getHowMuch() + " " +
                this.getWho() + " LV" + this.getLevel() + " per ricevere una pozione LV" + this.getPotionRewardLevel();
    }
}
