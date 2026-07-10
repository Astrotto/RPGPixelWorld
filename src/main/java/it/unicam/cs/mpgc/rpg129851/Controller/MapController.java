package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.SpawnPoint.setSpawnPoint;
import static it.unicam.cs.mpgc.rpg129851.Timeline.ChangeSceneTransition.startTransition;

import it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class MapController extends EntityController {
    @FXML
    private Rectangle blackScreen;
    @FXML
    private AnchorPane mapPane;
    public void initialize() {
        super.initialize();
        setKeyDetector();
        setBackgroundView("worldMap.png", backgroundView);
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
            timer.stop();
            startTransition(blackScreen, Duration.seconds(1.5), () -> changeMap("forest"));
        }
    }


    private void loadHitboxHome(){
        home.setLayoutX(590);
        home.setLayoutY(65);
    }

}
