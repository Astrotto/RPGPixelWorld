package it.unicam.cs.mpgc.rpg129851.Model;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;

import java.util.Random;

public class Strength extends Attribute {
    private int baseDamage;
    private int finalDamage;
    public Strength() {}

    public int getBaseDamage() {
        return baseDamage;
    }
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
    public int getFinalDamage() {
        return finalDamage;
    }
    public void setFinalDamage(int finalDamage) {
        this.finalDamage = finalDamage;
    }
}
