package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;

import javafx.animation.AnimationTimer;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class EntityController extends LoaderController {
    public AnimationTimer timer;
    public static double spawnX = -1;
    public static double spawnY = -1;

    public void initialize() {
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateLocation();
                setPotionObtained();
                player.getEntityView().loadPlayerAnimation(now);
                if(spawnX != -1 || spawnY != -1) {
                    player.getEntityView().setLayoutX(spawnX);
                    player.getEntityView().setLayoutY(spawnY);
                    spawnX = -1;
                    spawnY = -1;
                }
            }
        };
        player.getEntityView().setImage(imageLeft);
        timer.start();
    }

    public void updateLocation() {
        setNewX(player.getEntityView().getLayoutX());
        setNewY(player.getEntityView().getLayoutY());
        player.getEntityView().setIsMoving(false);

        keyDetection("X");
        keyDetection("Y");

        if(getNewX() < -60) setNewX(-60);
        if(getNewY() < -60) setNewY(-60);

        collisionDetection(gameWorld, getNewX(), getNewY());
        if(getNewY() < 300) {
            collisionDetection(home, getNewX(), getNewY());
        }else
            collisionDetection(graveyard, getNewX(), getNewY());
    }
    private void collisionDetection(Pane obstacle, double x, double y) {
        if(x > obstacle.getWidth() - player.getEntityView().getViewport().getWidth()) {
            setNewX(obstacle.getWidth() - player.getEntityView().getViewport().getWidth());
        }
        if(y > obstacle.getHeight() - player.getEntityView().getViewport().getHeight()){
            setNewY(obstacle.getHeight() - player.getEntityView().getViewport().getHeight());
        }
    }
    public void collisionDetection(Rectangle obstacle, double x, double y) {
        Bounds hitbox = obstacle.getBoundsInParent();

        if(!player.getHitbox(x + 70, y + 55).intersects(hitbox)) {
            player.getEntityView().setLayoutX(getNewX());
        }
        if(!player.getHitbox(player.getEntityView().getLayoutX() + 70, y + 55).intersects(hitbox)) {
            player.getEntityView().setLayoutY(getNewY());
        }
    }

    public void setSpawnPoint(double x, double y){
        spawnX = x;
        spawnY = y;

    }


}
