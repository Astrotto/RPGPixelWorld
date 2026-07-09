package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;

import it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MapController extends EntityController {
    @FXML
    private Rectangle blackScreen;
    @FXML
    private AnchorPane mapPane;
    public void initialize() {
        super.initialize();
        setKeyDetector();
        loadBackground("worldMap.png");
        loadHitboxHome();
        setSpawnPoint(600, 120);
    }


    public void setKeyDetector() {
        mapPane.setOnKeyPressed(KeyDetector::manageKeyPressed);
        mapPane.setOnKeyReleased(KeyDetector::manageKeyReleased);
    }
    public void updatePlayerLocation() {
        super.updatePlayerLocation();
        collisionDetectionForest();
    }
    private void collisionDetectionForest(){
        Bounds hitboxPlayer = player.getHitbox(player.getEntityView().getLayoutX() + 70, player.getEntityView().getLayoutY() + 55);
        Bounds hitboxForest = forest.getBoundsInParent();
        if(hitboxPlayer.intersects(hitboxForest)){
            //getKeyPressed().clear();
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
        changeMap((Stage)player.getEntityView().getView().getScene().getWindow(), "forest-view");
    }

    private void loadHitboxHome(){
        home.setLayoutX(590);
        home.setLayoutY(65);
    }

}
