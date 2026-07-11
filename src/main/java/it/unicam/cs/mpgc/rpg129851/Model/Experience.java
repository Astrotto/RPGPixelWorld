package it.unicam.cs.mpgc.rpg129851.Model;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;

public class Experience extends Statistic{
    private Level level;

    public Experience(int level, int currentExperience, int maxExperience) {
        super(currentExperience, maxExperience);
        this.level = new Level(level);
    }
    public void earnExperience(int experience) {
        if (experience <= 0) return;
        int currentEXP = this.getStatistic() + experience;
        while (currentEXP >= this.getMaxStatistic()) {
            if (level.increaseLevel()) {
                currentEXP -= this.getMaxStatistic();
                this.setMaxStatistic(this.getMaxStatistic() + 50);
            } else {
                currentEXP = this.getMaxStatistic();
                break;
            }
        }
        this.setStatistic(currentEXP);
    }
    public Level getLevel(){
        return this.level;
    }


}
