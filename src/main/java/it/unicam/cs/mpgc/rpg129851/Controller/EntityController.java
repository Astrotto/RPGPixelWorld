package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.Model.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Set;

public class EntityController extends LoaderController {

    public final Set<KeyCode> keyPressed = new HashSet<>();
    public boolean moving = false;
    public double newX, newY;

    public Pane gameWorld;
    public AnimationTimer timer;
    private long lastChangeFrame = 0;
    private int actualFrame = 0;

    public void initialize() {
        super.initialize();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateLocation();
                updateAnimation(now);
                loadHealthBarPlayer();
            }
        };
        timer.start();
    }
    public void updateAnimation(long actualHour) {
        if(!moving){
            playerView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        }else if(actualHour - lastChangeFrame > 100_000_000){
            actualFrame = (actualFrame + 1) % 6;
            double xMovement = actualFrame * FRAME_WIDTH;
            playerView.setViewport(new Rectangle2D(xMovement, 0, FRAME_WIDTH, FRAME_HEIGHT));
            lastChangeFrame = actualHour;
        }
    }
    public void updateLocation() {
        newX = playerView.getLayoutX();
        newY = playerView.getLayoutY();
        double oldY = newY;
        moving = false;

        Bounds hitboxHome = home.getBoundsInParent();

        keyDetectionY(KeyCode.S, KeyCode.DOWN);
        keyDetectionY(KeyCode.W, KeyCode.UP);
        keyDetectionX(KeyCode.A, KeyCode.LEFT);
        keyDetectionX(KeyCode.D, KeyCode.RIGHT);

        if(newX < 0) newX = 0;
        if(newY < 0) newY = 0;

        collisionDetection(gameWorld, newX, newY);

        if(!Main.player.getHitbox(newX, oldY).intersects(hitboxHome)) {
            playerView.setLayoutX(newX);
        }
        if(!Main.player.getHitbox(playerView.getLayoutX(), newY).intersects(hitboxHome)) {
            playerView.setLayoutY(newY);
        }
    }
    private void collisionDetection(Pane obstacle, double x, double y) {
        if(x > obstacle.getWidth() - playerView.getViewport().getWidth()) {
            newX = obstacle.getWidth() - playerView.getViewport().getWidth();
        }
        if(y > obstacle.getHeight() - playerView.getViewport().getHeight()){
            newY = obstacle.getHeight() - playerView.getViewport().getHeight();
        }
    }

    public void keyDetectionY(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode )  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.W || keyCode == KeyCode.UP){
                newY -= Main.player.getSpeed();
                playerView.setImage(imageUp);
            }else if(keyCode == KeyCode.S || keyCode == KeyCode.DOWN){
                newY += Main.player.getSpeed();
                playerView.setImage(imageDown);
            }
            moving = true;
        }
    }

    public void keyDetectionX(KeyCode keyCode, KeyCode keyCode2) {
        if (keyPressed.contains(keyCode)  || keyPressed.contains(keyCode2)){
            if(keyCode == KeyCode.A || keyCode == KeyCode.LEFT){
                newX -= Main.player.getSpeed();
                playerView.setImage(imageLeft);
            }else if(keyCode == KeyCode.D || keyCode == KeyCode.RIGHT){
                newX += Main.player.getSpeed();
                playerView.setImage(imageRight);
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




}
