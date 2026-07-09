package it.unicam.cs.mpgc.rpg129851.Model;

public class Statistic {
    private int statistic;
    private int maxStatistic;

    public Statistic(){}
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
    public int getStatistic() {
        return this.statistic;
    }
    public int getMaxStatistic() {
        return this.maxStatistic;
    }
    public void setBothStatistic(int statistic) {
        this.statistic = statistic;
    }
    public void setMaxStatistic(int maxStatistic) { this.maxStatistic = maxStatistic; }
    public void setBothStatistic(Health health) {
        this.statistic = health.getStatistic();
        this.maxStatistic = health.getMaxStatistic();
    }

}
