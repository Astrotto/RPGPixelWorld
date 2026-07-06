package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class GameProgressBar {
    private ImageView progressBarView;
    private Rectangle progressBar;
    private Image progressBarImage;
    private Entity entity;

    public GameProgressBar(Entity entity) {

            this.entity = entity;

    }
    public ImageView getProgressBarView() {
        return this.progressBarView;
    }
    public void setProgressBarView(ImageView progressBarView) {
        this.progressBarView = progressBarView;
    }
    public Rectangle getProgressBar() {
        return progressBar;
    }
    public void setProgressBar(Rectangle progressBar) {
        this.progressBar = progressBar;
    }
    public void loadHealthBar(){
        this.getProgressBarView().setImage(this.getProgressBarImage());
        this.getProgressBar().setWidth(entity.getHealth().getHealthPercentage() * 130);
        changeColorHealthBar();
        this.getProgressBar().setLayoutX(this.getProgressBarView().getLayoutX() + 50);
        this.getProgressBar().setLayoutY(this.getProgressBarView().getLayoutY() + 113);
        this.getProgressBarView().setSmooth(false);
    }
    public Image getProgressBarImage(){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/healthBarPlayer.png")));
    }
    public void changeColorHealthBar(){
        if(entity.getHealth().getHealthPercentage() > 0.60){
            this.getProgressBar().setFill(Color.GREEN);
        }else if(entity.getHealth().getHealthPercentage() <= 0.60 && entity.getHealth().getHealthPercentage() >= 0.30){
            this.getProgressBar().setFill(Color.ORANGE);
        }else {
            this.getProgressBar().setFill(Color.RED);
        }
    }
}
