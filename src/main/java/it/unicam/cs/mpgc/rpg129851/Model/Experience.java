package it.unicam.cs.mpgc.rpg129851.Model;

public class Experience extends Attribute{
    private int level;

    public Experience(int level, int currentExperience) {
        this.level = level;
        this.setCurrentStats(currentExperience);
        this.setMaxStats(100);
    }
    public int getLevel() {
        return level;
    }

    public void earnExperience(int experience) {
        if(this.getCurrentStats() + experience >= this.getMaxStats()) {
            this.setCurrentStats(this.getCurrentStats() + experience - this.getMaxStats());
            this.controlIncreaseLevel();
        }else{
            this.setCurrentStats(this.getCurrentStats() + experience);
        }
    }
    private void controlIncreaseLevel(){
        if(this.getLevel() + 1 <= 3) {
            this.increaseLevel();
        }else{
            this.setCurrentStats(this.getMaxStats());
        }
    }
    public void increaseLevel() {
        this.level++;
    }
}
