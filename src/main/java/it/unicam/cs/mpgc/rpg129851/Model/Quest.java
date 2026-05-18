package it.unicam.cs.mpgc.rpg129851.Model;

public class Quest {
    public String task;
    public int howMuch;
    public String who;
    public int level;
    public String reward;
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
    public String getReward(){
        return reward;
    }
    public String toString(){
        return "La quest e': " + this.getTask() + " " + this.getHowMuch() + " " +
                this.getWho() + " LV" + this.getLevel() + " per ricevere " + this.getReward();
    }
}
