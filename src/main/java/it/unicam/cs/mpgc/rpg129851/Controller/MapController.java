package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MapController extends EntityController {
    @FXML
    private Rectangle blackScreen;
    public void initialize() {
        super.initialize();
        loadBackground("worldMap.png");
        loadHitboxHome();
        setSpawnPoint(600, 120);
    }

    public void updateLocation() {
        super.updateLocation();
        collisionDetectionForest();
    }
    public void collisionDetection(Rectangle obstacle, double x, double y) {
        Bounds hitbox = obstacle.getBoundsInParent();

        if(!Main.player.getHitbox(newX + 70, y + 55).intersects(hitbox)) {
            playerView.setLayoutX(newX);
        }
        if(!Main.player.getHitbox(playerView.getLayoutX() + 70, newY + 55).intersects(hitbox)) {
            playerView.setLayoutY(newY);
        }
    }

    private void collisionDetectionForest(){
        Bounds hitboxPlayer = Main.player.getHitbox(playerView.getLayoutX() + 70, playerView.getLayoutY() + 55);
        Bounds hitboxForest = forest.getBoundsInParent();
        if(hitboxPlayer.intersects(hitboxForest)){
            keyPressed.clear();
            timer.stop();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), blackScreen);
            fadeOut.setFromValue(0.0);
            fadeOut.setToValue(1.0);
            fadeOut.setOnFinished(event -> {
                joinForest();
            });
            fadeOut.play();
        }
    }
    private void joinForest(){
        changeMap((Stage)playerView.getScene().getWindow(), "forest-view");
    }

    private void loadHitboxHome(){
        home.setLayoutX(590);
        home.setLayoutY(65);
    }

}
