package it.unicam.cs.mpgc.rpg129851.Model;

public class Health extends Attribute{

    public Health() {}

    public void heal(int hp) {
        this.setCurrentStats(Math.min(this.getCurrentStats() + hp, this.getMaxStats()));
    }
    public void heal(Potion potion) {
        this.setCurrentStats(Math.min(this.getCurrentStats() + potion.getHealth().getCurrentStats(), this.getMaxStats()));
    }
    public void takeDamage(int damage) {
        this.setCurrentStats(this.getCurrentStats() - damage);
        if(this.getCurrentStats() <= 0) this.setCurrentStats(0);
    }

}
