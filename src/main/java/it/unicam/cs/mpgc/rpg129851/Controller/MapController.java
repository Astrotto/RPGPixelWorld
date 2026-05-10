package it.unicam.cs.mpgc.rpg129851.Controller;


import javafx.animation.FadeTransition;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MapController extends EntityController {

    public void initialize() {
        super.initialize();
        loadBackground("worldMap.png");
        loadHitboxHome();
        playerView.setLayoutX(640);
        playerView.setLayoutY(165);
    }

    public void updateLocation() {
        super.updateLocation();
        collisionDetectionForest();
    }

    private void collisionDetectionForest(){
        Bounds hitboxPlayer = player.getHitbox(playerView.getLayoutX(), playerView.getLayoutY());
        Bounds hitboxForest = forest.getBoundsInParent();
        if(hitboxPlayer.intersects(hitboxForest)){
            keyPressed.clear();
            timer.stop();
            FadeTransition fade = new FadeTransition(Duration.seconds(2), gameWorld);
            fade.setOnFinished(event -> {
                joinForest();
            });
            fade.play();
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
