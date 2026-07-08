package it.unicam.cs.mpgc.rpg129851.Model;

public class Potion {
    private final int level;
    private Health health = new Health();
    public Potion(int level){
        if(level < 1 || level > 3){
            throw new IllegalArgumentException("Potion invalid");
        }else{
            this.level = level;
            switch (this.level){
                case 1:
                    this.health.setCurrentStats(10);
                    break;
                case 2:
                    this.health.setCurrentStats(20);
                    break;
                case 3:
                    this.health.setCurrentStats(40);
            }
        }
    }

    public Health getHealth(){
        return this.health;
    }
    public int getLevel(){
        return this.level;
    }
}
