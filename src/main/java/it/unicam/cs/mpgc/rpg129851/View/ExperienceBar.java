package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;

public class ExperienceBar extends GameProgressBar{

    public ExperienceBar(Entity entity){
        super(entity);
    }

    @Override
    public void showGameProgressBar(){
        this.loadGameProgressBar(this.getEntity().getExperience(), 85, 45, 122);
    }
}
