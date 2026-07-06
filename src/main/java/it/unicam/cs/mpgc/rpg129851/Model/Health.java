package it.unicam.cs.mpgc.rpg129851.Model;

public class Health {
    private int currentHealth;
    private int maxHealth;

    public Health() {}
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        if(maxHealth <= 0){
            throw new IllegalArgumentException("Max health must be a positive integer");
        }else{
            this.maxHealth = maxHealth;
        }
    }
    public double getHealthPercentage() {
        return (double) this.getCurrentHealth() / this.getMaxHealth();
    }
    public void heal(int hp) {
        this.setCurrentHealth(Math.min(this.getCurrentHealth() + hp, this.getMaxHealth()));
    }
    public void takeDamage(int damage) {
        this.setCurrentHealth(this.getCurrentHealth() - damage);
        if(this.getCurrentHealth() <= 0) this.setCurrentHealth(0);
    }

}
