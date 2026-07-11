package it.unicam.cs.mpgc.rpg129851.Model;

import javafx.geometry.Bounds;

public abstract class Entity {
    private final String name;
    private final Attack attack = new Attack(new Strength());
    private final Defense defense = new Defense();
    private final Health health = new Health();
    private final Experience experience;

    public Entity(String name, int experience, int level) {
        if(name == null || experience < 0) {
            throw new IllegalArgumentException("Entity invalid");
        }
        this.name = name;
        this.experience = new Experience(level, experience, 85 * level);
    }

    public abstract Bounds getHitbox(double x, double y);
    public abstract void updateStatistic(int level, int experience);

    public String getName() {
        return this.name;
    }
    public boolean isAlive() {
        return this.health.getStatistic() > 0;
    }
    public Attack getAttack(){
        return this.attack;
    }
    public Defense getDefense(){
        return this.defense;
    }
    public Health getHealth() {
        return this.health;
    }
    public Experience getExperience() {
        return this.experience;
    }
}
