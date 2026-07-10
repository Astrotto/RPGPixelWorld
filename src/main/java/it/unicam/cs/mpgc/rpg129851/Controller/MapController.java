package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.player;
import static it.unicam.cs.mpgc.rpg129851.Movement.SpawnPoint.setSpawnPoint;
import static it.unicam.cs.mpgc.rpg129851.Timeline.ChangeSceneTransition.startTransition;

public class MapController extends EntityController {
    @FXML
    private Rectangle forest, blackScreen;
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
        PlayerView view = ViewRegister.ofPlayer(player);
        Bounds hitboxPlayer = player.getHitbox(view.getLayoutX() + 70, view.getLayoutY() + 55);
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
