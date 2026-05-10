package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class ForestController extends EntityController {
    @FXML
    private ImageView exclamation;
    Orc orc;

    public void initialize() {
        super.initialize();
        placeOrcRandomly();
    }
    public void updateLocation() {
        super.updateLocation();
        orcCollisionDetection();
    }
    private void placeOrcRandomly(){
        Random rand  = new Random();
        orc = new Orc(45,65, 20, 10);

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
        Bounds hitboxPlayer = player.getHitbox(playerView.getLayoutX(), playerView.getLayoutY());
        Bounds hitboxOrc = orc.getHitbox(orcView.getLayoutX(), orcView.getLayoutY());
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
        healthBar.setWidth(healthBar.getWidth()*0.25);
        BattleController battleController = new BattleController();
        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(event -> {
            battleController.setData(player, orc);
            startBattle();
        });
        pause.play();
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
