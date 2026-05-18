package it.unicam.cs.mpgc.rpg129851.Model;

public class Potion {
    private int level;
    private int health;
    public Potion(int level){
        if(level < 1 || level > 3){
            throw new IllegalArgumentException("Potion invalid");
        }else{
            this.level = level;
            switch (this.level){
                case 1:
                    this.health = 10;
                    break;
                case 2:
                    this.health = 20;
                    break;
                case 3:
                    this.health = 40;
            }
        }
    }
    public int getHealth(){
        return this.health;
    }
}
