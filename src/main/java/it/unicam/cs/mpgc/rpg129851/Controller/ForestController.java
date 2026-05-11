package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class ForestController extends EntityController {
    @FXML
    private ImageView exclamation;
    @FXML
    private Rectangle blackScreen, leftExit, rightExit, upExit, downExit;
    private Bounds rightExitHitbox, leftExitHitbox, upExitHitbox, downExitHitbox;

    public void initialize() {
        super.initialize();
        loadBoundsHitbox();
        loadBackground("forestMap.png");
        placeOrcRandomly();
    }
    public void updateLocation() {
        super.updateLocation();
        exitCollision(leftExitHitbox, 8, 370);
        exitCollision(rightExitHitbox, 220, 380);
        exitCollision(upExitHitbox, 105, 225);
        exitCollision(downExitHitbox, 122, 470);
        orcCollisionDetection();
    }
    public void exitCollision(Bounds exit, double x, double y){
        if(Main.player.getHitbox(newX, newY).intersects(exit)) {
            timer.stop();
            keyPressed.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), blackScreen);
            fadeOut.setFromValue(0.0);
            fadeOut.setToValue(1.0);
            fadeOut.setOnFinished(event -> {
                changeMap((Stage)playerView.getScene().getWindow(), "map-view");
                setSpawnPoint(x, y);
            });
            fadeOut.play();

        }
    }
    private void loadBoundsHitbox(){
        leftExitHitbox = leftExit.getBoundsInParent();
        rightExitHitbox = rightExit.getBoundsInParent();
        upExitHitbox = upExit.getBoundsInParent();
        downExitHitbox = downExit.getBoundsInParent();
    }
    private void placeOrcRandomly(){
        Random rand  = new Random();

        double orcWidth = orcView.getBoundsInParent().getWidth();
        double orcHeight = orcView.getBoundsInParent().getHeight();

        double minX = forest.getLayoutX();
        double maxX = minX + forest.getWidth() - orcWidth;

        double minY = forest.getLayoutY();
        double maxY = minY + forest.getHeight() - orcHeight;

        double randomX = minX + (maxX - minX) * rand.nextDouble();
        double randomY = minY + (maxY - minY) * rand.nextDouble();
        orcView.setVisible(true);
        orcView.setLayoutX(randomX);
        orcView.setLayoutY(randomY);
    }
    private void orcCollisionDetection(){
        Bounds hitboxPlayer = Main.player.getHitbox(playerView.getLayoutX(), playerView.getLayoutY());
        Bounds hitboxOrc = Main.orc.getHitbox(orcView.getLayoutX(), orcView.getLayoutY());
        if(hitboxPlayer.intersects(hitboxOrc)) {
            encounterEntity();
        }else{
            //orcView.setVisible(false);
        }
    }
    private void encounterEntity() {
        loadExclamation();
        orcView.setVisible(true);
        keyPressed.clear();
        timer.stop();
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), blackScreen);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(1.0);
        fadeOut.setOnFinished(event -> {
            startBattle();
        });
        fadeOut.play();
    }

    private void startBattle() {
        exclamation.setVisible(false);
        changeMap((Stage)playerView.getScene().getWindow(), "battle-view");
    }
    private void loadExclamation(){
        loadExclamationImage();
        exclamation.setX(playerView.getLayoutX() + 20);
        exclamation.setY(playerView.getLayoutY() - 20);
        exclamation.setRotate(5);
        exclamation.setImage(imageExclamation);
    }
    private void loadExclamationImage(){
        imageExclamation = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/exclamation.png")));
    }

}
