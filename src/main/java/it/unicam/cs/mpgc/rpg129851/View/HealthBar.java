package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.scene.paint.Color;

public class HealthBar extends GameProgressBar{

    public HealthBar(Entity entity){
        super(entity);
    }

    @Override
    public void showGameProgressBar(){
        this.loadGameProgressBar(this.getEntity().getHealth() ,130, 50, 113);
        changeColorProgressBar();
    }
    public void changeColorProgressBar(){
        if(this.getEntity().getHealth().getStatisticPercentage() > 0.60){
            this.getProgressBar().setFill(Color.GREEN);
        }else if(this.getEntity().getHealth().getStatisticPercentage() <= 0.60 && this.getEntity().getHealth().getStatisticPercentage() >= 0.30){
            this.getProgressBar().setFill(Color.ORANGE);
        }else {
            this.getProgressBar().setFill(Color.RED);
        }
    }

}
