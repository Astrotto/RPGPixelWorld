package it.unicam.cs.mpgc.rpg129851.Model;

public class Experience extends Statistic{
    private int level;

    public Experience(int level, int currentExperience, int maxExperience) {
        super(currentExperience, maxExperience);
        this.level = level;
    }
    public void earnExperience(int experience) {
        if(this.getStatistic() + experience >= this.getMaxStatistic()) {
            this.setStatistic(this.getStatistic() + experience - this.getMaxStatistic());
            this.controlIncreaseLevel();
        }else{
            this.setStatistic(this.getStatistic() + experience);
        }
    }
    private void controlIncreaseLevel(){
        if(this.getLevel() + 1 <= 3) {
            this.increaseLevel();
        }else{
            this.setStatistic(this.getMaxStatistic());
        }
    }
    public int getLevel() {
        return level;
    }
    public void increaseLevel() {
        this.level++;

    }


}
