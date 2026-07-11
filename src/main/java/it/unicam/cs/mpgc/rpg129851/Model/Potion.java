package it.unicam.cs.mpgc.rpg129851.Model;

public class Potion {
    private final Level level;
    private final Health health;

    public Potion(int level){
        this.level = new Level(level);
        if(level < 1 || level > this.getLevel().getMaxLevel()){
            throw new IllegalArgumentException("Potion invalid");
        }
        this.health = new Health(healAmount(level));
    }
    private int healAmount(int level){
        return switch (level) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            default -> 0;
        };
    }
    public Health getHealth(){
        return this.health;
    }
    public Level getLevel(){
        return this.level;
    }
}
