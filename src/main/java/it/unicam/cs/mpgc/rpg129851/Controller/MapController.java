package it.unicam.cs.mpgc.rpg129851.Controller;


import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class MapController extends ParentController {

    @FXML
    private ImageView exclamation;


    public void initialize() {
        super.initialize();
        loadBackground("worldMap.png");
        loadHitboxHome();
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
    private void orcCollisionDetection(){
        Bounds hitboxPlayer = player.getHitbox(playerView.getLayoutX(), playerView.getLayoutY());
        Bounds hitboxOrc = orc.getHitbox(orcView.getLayoutX(), orcView.getLayoutY());
        if(hitboxPlayer.intersects(hitboxOrc)) {
            encounterEntity();
        }else{
            orcView.setVisible(false);
        }
    }

    private void encounterEntity() {
        loadExclamation();
        orcView.setVisible(true);
        keyPressed.clear();
        timer.stop();
        healthBar.setWidth(healthBar.getWidth()*0.25);
        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(event -> {
            startBattle();
        });
        pause.play();
    }
    private void startBattle() {
        try{
            exclamation.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/it/unicam/cs/mpgc/rpg129851/battle-view.fxml")));
            Parent root = loader.load();
            BattleController battleController = loader.getController();
            battleController.setData(player, orc);
            Stage stage = (Stage) playerView.getScene().getWindow();
            Scene battleScene = new Scene(root);
            stage.setScene(battleScene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Loading error of the battle scene");
        }
    }


    private void loadExclamation(){
        loadExclamationImage();
        exclamation.setX(playerView.getLayoutX() + 20);
        exclamation.setY(playerView.getLayoutY() - 20);
        exclamation.setRotate(5);
        exclamation.setImage(imageExclamation);
    }
    public void loadExclamationImage(){
        imageExclamation = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/images/exclamation.png")));
    }


    private void loadHitboxHome(){
        home.setLayoutX(570);
        home.setLayoutY(55);
    }

}
