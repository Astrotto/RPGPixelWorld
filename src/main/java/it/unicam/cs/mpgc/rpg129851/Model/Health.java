package it.unicam.cs.mpgc.rpg129851.Model;

public class Health extends Statistic {

    public Health(int hp, int maxHp) {
        super(hp, maxHp);
    }
    public Health(int hp){
        super(hp);
    }
    public Health(){ super(); }

    public void heal(int hp) {
        this.setBothStatistic(Math.min(this.getStatistic() + hp, this.getMaxStatistic()));
    }
    public void heal(Potion potion) {
        this.setBothStatistic(Math.min(this.getStatistic() + potion.getHealth().getStatistic(), this.getMaxStatistic()));
    }
    public void takeDamage(int damage) {
        this.setBothStatistic(this.getStatistic() - damage);
        if(this.getStatistic() <= 0) this.setBothStatistic(0);
    }

}
