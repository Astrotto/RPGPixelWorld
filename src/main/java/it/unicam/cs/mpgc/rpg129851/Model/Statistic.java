package it.unicam.cs.mpgc.rpg129851.Model;

public class Statistic {
    private int statistic;
    private int maxStatistic;

    public Statistic(int statistic, int maxStatistic) {
        if(statistic < 0 || maxStatistic < 0 || maxStatistic < statistic)
            throw new IllegalArgumentException("Statistic, maxStatistic can't be negative or maxStatistic can't be < statistic");
        else {
            this.statistic = statistic;
            this.maxStatistic = maxStatistic;
        }
    }
    public Statistic(int statistic) {
        if(statistic < 0)
            throw new IllegalArgumentException("Statistic can't be < 0");
        else
            this.statistic = statistic;
    }
    public double getStatisticPercentage() {
        return (double) this.getStatistic() / this.getMaxStatistic();
    }
    public void setStatistic(int statistic) {
        this.statistic = statistic;
    }
    public int getStatistic() {
        return this.statistic;
    }
    public int getMaxStatistic() {
        return this.maxStatistic;
    }

}
