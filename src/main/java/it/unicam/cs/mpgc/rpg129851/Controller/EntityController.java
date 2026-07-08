package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.util.HashSet;
import java.util.Set;

public class EntityController extends LoaderController {
    public final Set<KeyCode> keyPressed = new HashSet<>();
    public double newX, newY;
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
        newX = player.getEntityView().getLayoutX();
        newY = player.getEntityView().getLayoutY();
        player.getEntityView().setIsMoving(false);
        double oldY = newY;

        keyDetectionY(KeyCode.S, KeyCode.DOWN);
        keyDetectionY(KeyCode.W, KeyCode.UP);
        keyDetectionX(KeyCode.A, KeyCode.LEFT);
        keyDetectionX(KeyCode.D, KeyCode.RIGHT);

        if(newX < -60) newX = -60;
        if(newY < -60) newY = -60;

        collisionDetection(gameWorld, newX, newY);
        if(newY < 300) {
            collisionDetection(home, newX, newY, oldY);
        }else
            collisionDetection(graveyard, newX, newY, oldY);
    }
    private void collisionDetection(Pane obstacle, double x, double y) {
        if(x > obstacle.getWidth() - player.getEntityView().getViewport().getWidth()) {
            newX = obstacle.getWidth() - player.getEntityView().getViewport().getWidth();
        }
        if(y > obstacle.getHeight() - player.getEntityView().getViewport().getHeight()){
            newY = obstacle.getHeight() - player.getEntityView().getViewport().getHeight();
        }
    }
    public void collisionDetection(Rectangle obstacle, double x, double y, double oldY) {
        Bounds hitbox = obstacle.getBoundsInParent();

        if(!player.getHitbox(newX + 70, oldY + 55).intersects(hitbox)) {
            player.getEntityView().setLayoutX(newX);
        }
        if(!player.getHitbox(player.getEntityView().getLayoutX() + 70, newY + 55).intersects(hitbox)) {
            player.getEntityView().setLayoutY(newY);
        }
    }


    public void keyDetectionY(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode )  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.W || keyCode == KeyCode.UP){
                newY -= player.getSpeed();
            }else if(keyCode == KeyCode.S || keyCode == KeyCode.DOWN){
                newY += player.getSpeed();
            }
            player.getEntityView().setIsMoving(true);
        }
    }

    public void keyDetectionX(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode)  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.A || keyCode == KeyCode.LEFT){
                newX -= player.getSpeed();
                player.getEntityView().setImage(imageLeft);
            }else if(keyCode == KeyCode.D || keyCode == KeyCode.RIGHT){
                newX += player.getSpeed();
                player.getEntityView().setImage(imageRight);
            }
            player.getEntityView().setIsMoving(true);
        }

    }
    @FXML
    void manageKeyPressed(KeyEvent event) {
        keyPressed.add(event.getCode());
    }

    @FXML
    void manageKeyReleased(KeyEvent event) {
        keyPressed.remove(event.getCode());
    }

    public void setSpawnPoint(double x, double y){
        spawnX = x;
        spawnY = y;

    }


}
