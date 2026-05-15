package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class ForestController extends EntityController {
    @FXML
    private ImageView exclamation;
    @FXML
    private Pane orcSpawn;
    @FXML
    private Rectangle blackScreen,
            leftExit, rightExit, upExit, downExit,
            spawnLeftDownCorner, spawnLeftUpCorner, spawnRightUpCorner, spawnRightDownCorner;
    private Bounds rightExitHitbox, leftExitHitbox, upExitHitbox, downExitHitbox;

    public void initialize() {
        super.initialize();
        loadBoundsHitbox();
        loadBackground("forestMap.png");
        Main.orcs.clear();
        placeOrcRandomly();
    }
    public void updateLocation() {
        super.updateLocation();
        exitCollision(leftExitHitbox, -10, 370);
        exitCollision(rightExitHitbox, 220, 380);
        exitCollision(upExitHitbox, 105, 225);
        exitCollision(downExitHitbox, 122, 470);
        orcCollisionDetection(Main.orcs);
    }
    public void exitCollision(Bounds exit, double x, double y){
        if(Main.player.getHitbox(newX + 70, newY + 55).intersects(exit)) {
            timer.stop();
            keyPressed.clear();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), blackScreen);
            fadeOut.setFromValue(0.0);
            fadeOut.setToValue(1.0);
            fadeOut.setOnFinished(event -> {
                changeMap((Stage)playerView.getScene().getWindow(), "map-view");
                setSpawnPoint(x - 40, y - 40);
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
        placeOrcRandomlyInACorner(spawnLeftDownCorner);
        placeOrcRandomlyInACorner(spawnLeftUpCorner);
        placeOrcRandomlyInACorner(spawnRightUpCorner);
        placeOrcRandomlyInACorner(spawnRightDownCorner);
    }
    private void placeOrcRandomlyInACorner(Rectangle spawnCorner) {
        Random rand = new Random();

        int numeroOrchi = rand.nextInt(4) + 1;
        for (int i = 0; i < numeroOrchi; i++) {
            int randomLevel = rand.nextInt(10) + 1;
            int lvlOrc = 0;
            if(randomLevel <= 6){
                lvlOrc = 1;
            }else if(randomLevel <= 9){
                lvlOrc = 2;
            }else{
                lvlOrc = 3;
            }
            Orc orc = new Orc(lvlOrc);

            if(lvlOrc == 1){
                loadImagesOrc1();
            }else if(lvlOrc == 2){
                loadImagesOrc2();
            }else{
                loadImagesOrc3();
            }
            ImageView orcView = new ImageView(imageOrc);
            orcView.setViewport(new Rectangle2D(0, 0 , 100, 100));
            Main.orcs.put(orc, orcView);

            orcView.setFitWidth(260);
            orcView.setFitHeight(260);
            orcView.setPreserveRatio(true);

            double orcWidth = orcView.getBoundsInParent().getWidth();
            double orcHeight = orcView.getBoundsInParent().getHeight();

            double minX = spawnCorner.getLayoutX();
            double maxX = minX + spawnCorner.getWidth() - orcWidth;

            double minY = spawnCorner.getLayoutY();
            double maxY = minY + spawnCorner.getHeight() - orcHeight;

            double randomX = minX + (maxX - minX) * rand.nextDouble();
            double randomY = minY + (maxY - minY) * rand.nextDouble();

            //orcView.setVisible(false);

            orcView.setLayoutX(randomX);
            orcView.setLayoutY(randomY);

            orcSpawn.getChildren().add(orcView);
        }
    }


    private void orcCollisionDetection(Map<Orc, ImageView> orcMap){
        Bounds hitboxPlayer = Main.player.getHitbox(playerView.getLayoutX() + 70, playerView.getLayoutY() + 55);
        orcMap.forEach((orc, orcView) -> {
            Bounds hitboxOrc = orc.getHitbox(orcView.getLayoutX() + 70, orcView.getLayoutY() + 55);
            if(hitboxPlayer.intersects(hitboxOrc)) {
                Main.setOrcEncountered(orc);
                encounterEntity(orcView);
            }
        });
    }
    private void encounterEntity(ImageView orcView) {
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
        exclamation.setX(playerView.getLayoutX() + 90);
        exclamation.setY(playerView.getLayoutY() + 55);
        exclamation.setRotate(5);
        exclamation.setImage(imageExclamation);
    }
    private void loadExclamationImage(){
        imageExclamation = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/exclamation.png")));
    }

}
