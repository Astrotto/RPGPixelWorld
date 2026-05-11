package it.unicam.cs.mpgc.rpg129851.Model;

import it.unicam.cs.mpgc.rpg129851.Interfaces.EntityHitbox;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public class Orc extends Entity implements EntityHitbox {
    int lootExp;
    public Orc(int lootExp, int hp, int strength, int defense) {
        super("Orc", 65, hp, strength, defense);
        this.lootExp = lootExp;
    }
    public Bounds getHitbox(double x, double y) {
        return new BoundingBox(x + 17, y + 15, 20, 22);
    }
    public int getLootExp() {
        return lootExp;
    }
}
