package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
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
        setSpawnPoint(640, 165);
    }

    public void updateLocation() {
        super.updateLocation();
        collisionDetectionHome();
        collisionDetectionForest();
    }
    public void collisionDetectionHome() {
        Bounds hitboxHome = home.getBoundsInParent();
        double oldY = newY;

        if(!Main.player.getHitbox(newX, oldY).intersects(hitboxHome)) {
            playerView.setLayoutX(newX);
        }
        if(!Main.player.getHitbox(playerView.getLayoutX(), newY).intersects(hitboxHome)) {
            playerView.setLayoutY(newY);
        }
    }
    private void collisionDetectionForest(){
        Bounds hitboxPlayer = Main.player.getHitbox(playerView.getLayoutX(), playerView.getLayoutY());
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
        home.setLayoutX(570);
        home.setLayoutY(55);
    }

}
