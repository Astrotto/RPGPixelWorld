package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.animation.*;
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
    private Rectangle blackScreen;

    public void initialize() {
        super.initialize();
        loadBackground("forestMap.png");
        placeOrcRandomly();
    }
    public void updateLocation() {
        super.updateLocation();
        orcCollisionDetection();
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
        Main.player.takeDamage(25);
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
