package it.unicam.cs.mpgc.rpg129851.Model;

public class Attribute {
    private int currentStats;
    private int maxStats;

    public Attribute(){}

    public double getStatsPercentage() {
        return (double) this.getCurrentStats() / this.getMaxStats();
    }

    public int getCurrentStats() { return currentStats; }
    public int getMaxStats() { return maxStats; }
    public void setCurrentStats(int currentStats) {
        this.currentStats = currentStats;
    }
    public void setMaxStats(int maxStats) {
        if(maxStats <= 0){
            throw new IllegalArgumentException("Max stats must be a positive integer");
        }else{
            this.maxStats = maxStats;
        }
    }

}
