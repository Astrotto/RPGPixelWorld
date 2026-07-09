package it.unicam.cs.mpgc.rpg129851.Model;

public class Experience extends Statistic{
    private int level;

    public Experience(int level, int currentExperience, int maxExperience) {
        super(currentExperience, maxExperience);
        this.level = level;
    }
    public void earnExperience(Entity entity, int experience) {
        if(this.getStatistic() + experience >= this.getMaxStatistic()) {
            this.setStatistic(this.getStatistic() + experience - this.getMaxStatistic());
            this.controlIncreaseLevel(entity);
        }else{
            this.setStatistic(this.getStatistic() + experience);
        }
    }
    private void controlIncreaseLevel(Entity entity) {
        if(this.getLevel() + 1 <= 3) {
            this.increaseLevel();
            this.increaseExperience();
            entity.updateStatistic();
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
    private void increaseExperience(){
        this.setStatistic(this.getStatistic() + 50);
        this.setMaxStatistic(this.getMaxStatistic() + 50);
    }


}
