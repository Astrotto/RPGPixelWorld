package it.unicam.cs.mpgc.rpg129851.Collision;

import it.unicam.cs.mpgc.rpg129851.Model.Player;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;

public class ObstacleCollisionChecker {
    public static void obstacleCollisionDetection(Player player, Rectangle obstacle, double x, double y) {
        PlayerView view = ViewRegister.ofPlayer(player);
        Bounds obstacleHitbox = obstacle.getBoundsInParent();
        if(!player.getHitbox(x + 70, y + 55).intersects(obstacleHitbox)) {
            view.setLayoutX(getNewX());
        }
        if(!player.getHitbox(view.getLayoutX() + 70, y + 55).intersects(obstacleHitbox)) {
            view.setLayoutY(getNewY());
        }
    }
    public static void worldCollisionDetection(Player player, Pane obstacle, double x, double y) {
        PlayerView view = ViewRegister.ofPlayer(player);
        if(x > obstacle.getWidth() - view.getViewport().getWidth()) {
            setNewX(obstacle.getWidth() - view.getViewport().getWidth());
        }
        if(y > obstacle.getHeight() - view.getViewport().getHeight()){
            setNewY(obstacle.getHeight() - view.getViewport().getHeight());
        }
    }
}
