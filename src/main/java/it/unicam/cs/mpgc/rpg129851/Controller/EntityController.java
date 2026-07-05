package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
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
    public boolean moving = false;
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
                loadAnimation(now);
                if(spawnX != -1 || spawnY != -1) {
                    Main.player.getEntityView().setLayoutX(spawnX);
                    Main.player.getEntityView().setLayoutY(spawnY);
                    spawnX = -1;
                    spawnY = -1;
                }
            }
        };
        Main.player.getEntityView().setImage(imageLeft);
        timer.start();
    }
    public void loadAnimation(long actualHour) {
        if(!moving){
            Main.player.getEntityView().setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - Main.player.getLastChangeFrame() > 100_000_000){
            Main.player.setActualFrame((Main.player.getActualFrame() + 1) % 8);
            double xMovement = Main.player.getActualFrame() * FRAME_WIDTH;
            Main.player.getEntityView().setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            Main.player.setLastChangeFrame(actualHour);
        }
    }
    public void updateLocation() {
        newX = Main.player.getEntityView().getLayoutX();
        newY = Main.player.getEntityView().getLayoutY();
        moving = false;
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
        if(x > obstacle.getWidth() - Main.player.getEntityView().getViewport().getWidth()) {
            newX = obstacle.getWidth() - Main.player.getEntityView().getViewport().getWidth();
        }
        if(y > obstacle.getHeight() - Main.player.getEntityView().getViewport().getHeight()){
            newY = obstacle.getHeight() - Main.player.getEntityView().getViewport().getHeight();
        }
    }
    public void collisionDetection(Rectangle obstacle, double x, double y, double oldY) {
        Bounds hitbox = obstacle.getBoundsInParent();

        if(!Main.player.getHitbox(newX + 70, oldY + 55).intersects(hitbox)) {
            Main.player.getEntityView().setLayoutX(newX);
        }
        if(!Main.player.getHitbox(Main.player.getEntityView().getLayoutX() + 70, newY + 55).intersects(hitbox)) {
            Main.player.getEntityView().setLayoutY(newY);
        }
    }


    public void keyDetectionY(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode )  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.W || keyCode == KeyCode.UP){
                newY -= Main.player.getSpeed();
            }else if(keyCode == KeyCode.S || keyCode == KeyCode.DOWN){
                newY += Main.player.getSpeed();
            }
            moving = true;
        }
    }

    public void keyDetectionX(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode)  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.A || keyCode == KeyCode.LEFT){
                newX -= Main.player.getSpeed();
                Main.player.getEntityView().setImage(imageLeft);
            }else if(keyCode == KeyCode.D || keyCode == KeyCode.RIGHT){
                newX += Main.player.getSpeed();
                Main.player.getEntityView().setImage(imageRight);
            }
            moving = true;
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
