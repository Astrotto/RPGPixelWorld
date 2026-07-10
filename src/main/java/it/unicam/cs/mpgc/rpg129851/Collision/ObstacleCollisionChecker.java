package it.unicam.cs.mpgc.rpg129851.Collision;

import it.unicam.cs.mpgc.rpg129851.Model.Player;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.setNewY;

public class ObstacleCollisionChecker {
    public static void obstacleCollisionDetection(Player player, Rectangle obstacle, double x, double y) {
        Bounds obstacleHitbox = obstacle.getBoundsInParent();
        if(!player.getHitbox(x + 70, y + 55).intersects(obstacleHitbox)) {
            player.getEntityView().setLayoutX(getNewX());
        }
        if(!player.getHitbox(player.getEntityView().getLayoutX() + 70, y + 55).intersects(obstacleHitbox)) {
            player.getEntityView().setLayoutY(getNewY());
        }
    }
    public static void worldCollisionDetection(Player player, Pane obstacle, double x, double y) {
        if(x > obstacle.getWidth() - player.getEntityView().getViewport().getWidth()) {
            setNewX(obstacle.getWidth() - player.getEntityView().getViewport().getWidth());
        }
        if(y > obstacle.getHeight() - player.getEntityView().getViewport().getHeight()){
            setNewY(obstacle.getHeight() - player.getEntityView().getViewport().getHeight());
        }
    }
}