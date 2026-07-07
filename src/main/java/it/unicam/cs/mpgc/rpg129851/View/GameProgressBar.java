package it.unicam.cs.mpgc.rpg129851.View;

import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class GameProgressBar {
    private ImageView progressBarView;
    private Rectangle progressBar;
    private final Entity entity;

    public GameProgressBar(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setBar(ImageView progressBarView,  Rectangle progressBar) {
        this.setProgressBarView(progressBarView);
        this.setProgressBar(progressBar);
    }

    public void setProgressBarView(ImageView progressBarView) {
        this.progressBarView = progressBarView;
    }
    public ImageView getProgressBarView(){
        return this.progressBarView;
    }
    public Rectangle getProgressBar() {
        return progressBar;
    }
    public void setProgressBar(Rectangle progressBar) {
        this.progressBar = progressBar;
    }
    public Image getProgressBarImage(){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/progressBarImage.png")));
    }
    public void loadGameProgressBar(int length, int x, int y){
        this.getProgressBarView().setImage(this.getProgressBarImage());
        this.getProgressBar().setWidth(this.getEntity().getHealth().getStatsPercentage() * length);
        this.getProgressBar().setLayoutX(this.getProgressBarView().getLayoutX() + x);
        this.getProgressBar().setLayoutY(this.getProgressBarView().getLayoutY() + y);
        this.getProgressBarView().setSmooth(false);
    }
}
