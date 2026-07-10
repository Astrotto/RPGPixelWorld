package it.unicam.cs.mpgc.rpg129851.Model;

public class Experience extends Statistic{
    private Level level;

    public Experience(int level, int currentExperience, int maxExperience) {
        super(currentExperience, maxExperience);
        this.level = new Level(level);
    }
    public void earnExperience(Entity entity, int experience) {
        if (experience <= 0) return;
        int currentEXP = this.getStatistic() + experience;
        while (currentEXP >= this.getMaxStatistic()) {

            if (level.increaseLevel()) {
                currentEXP -= this.getMaxStatistic();

                this.setMaxStatistic(this.getMaxStatistic() + 50);

                entity.updateStatistic(this.level.getLevel());
            } else {
                currentEXP = this.getMaxStatistic();
                break;
            }
        }
        this.setStatistic(currentEXP);
    }
    public int getLevel(){
        return this.level.getLevel();
    }


}
