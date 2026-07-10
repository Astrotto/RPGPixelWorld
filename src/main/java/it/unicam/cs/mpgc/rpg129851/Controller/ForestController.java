package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;

import static it.unicam.cs.mpgc.rpg129851.Controller.OrcController.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class ForestController extends EntityController {
    @FXML
    public AnchorPane forestPane;
    @FXML
    private ImageView exclamation;
    @FXML
    private Pane orcSpawn;
    @FXML
    private Circle forestSpirit;
    @FXML
    private Rectangle blackScreen,
            leftExit, rightExit, upExit, downExit,
            spawnLeftDownCorner, spawnLeftUpCorner, spawnRightUpCorner, spawnRightDownCorner;
    private Bounds rightExitHitbox, leftExitHitbox, upExitHitbox, downExitHitbox;
    private static boolean questReceived = false;
    private static int howMuch;

    public void initialize() {
        super.initialize();
        setKeyDetector();
        loadBoundsHitbox();
        setBackgroundView("forestMap.png", backgroundView);
        Main.orcs.clear();
        placeOrcRandomly();
    }
    public void setKeyDetector() {
        forestPane.setOnKeyPressed(KeyDetector::manageKeyPressed);
        forestPane.setOnKeyReleased(KeyDetector::manageKeyReleased);
    }
    public void updatePlayerLocation() {
        super.updatePlayerLocation();
        exitCollision(leftExitHitbox, -10, 370);
        exitCollision(rightExitHitbox, 220, 380);
        exitCollision(upExitHitbox, 105, 225);
        exitCollision(downExitHitbox, 122, 470);
        orcCollisionDetection(Main.orcs);
        meetForestSpirit();
    }
    public void exitCollision(Bounds exit, double x, double y){
        if(Main.player.getHitbox(getNewX() + 70, getNewY() + 55).intersects(exit)) {
            timer.stop();


            //getKeyPressed().clear();



            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), blackScreen);
            fadeOut.setFromValue(0.0);
            fadeOut.setToValue(1.0);
            fadeOut.setOnFinished(event -> {
                changeMap((Stage)Main.player.getEntityView().getView().getScene().getWindow(), "map");
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
        placeOrcRandomlyInACorner(spawnLeftDownCorner, orcSpawn);
        placeOrcRandomlyInACorner(spawnLeftUpCorner, orcSpawn);
        placeOrcRandomlyInACorner(spawnRightUpCorner, orcSpawn);
        placeOrcRandomlyInACorner(spawnRightDownCorner, orcSpawn);
    }



    private void orcCollisionDetection(List<Orc> orcList){
        Bounds hitboxPlayer = Main.player.getHitbox(Main.player.getEntityView().getLayoutX() + 70, Main.player.getEntityView().getLayoutY() + 55);
        orcList.forEach((orc) -> {
            Bounds hitboxOrc = orc.getHitbox(orc.getEntityView().getLayoutX() + 70, orc.getEntityView().getLayoutY() + 55);
            if(hitboxPlayer.intersects(hitboxOrc)) {
                Main.setOrcEncountered(orc);
                encounterEntity(orc.getEntityView().getView());
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
        changeMap((Stage)Main.player.getEntityView().getView().getScene().getWindow(), "battle");
    }
    private void loadExclamation(){
        exclamation.setX(Main.player.getEntityView().getLayoutX() + 90);
        exclamation.setY(Main.player.getEntityView().getLayoutY() + 55);
        exclamation.setRotate(5);
        exclamation.setImage(loadExclamationImage());
    }
    private Image loadExclamationImage(){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/exclamation.png")));
    }
    private void meetForestSpirit(){
        Bounds hitboxPlayer = Main.player.getHitbox(Main.player.getEntityView().getLayoutX() + 70, Main.player.getEntityView().getLayoutY() + 55);
        Bounds hitboxForestSpirit = forestSpirit.getBoundsInParent();
        if(hitboxPlayer.intersects(hitboxForestSpirit)) {
            if(!questReceived){
                System.out.println(Main.guardian.getRandomQuest().toString());
                howMuch = Main.guardian.getQuestReceived().getHowMuch();
                questReceived = true;
            }
        }
    }
    public static void questCompletedControl(){
        if(questReceived){
            if(Main.orcEncountered.getExperience().getLevel() == Main.guardian.getQuestReceived().getLevel() && Main.guardian.getQuestReceived().getHowMuch() >= 1){
                howMuch--;
                if(howMuch < 1) {
                    System.out.println("Hai completato la quest");
                    Main.player.getInventory().addPotion(Main.guardian.getPotionReward(Main.guardian.getQuestReceived().getPotionRewardLevel()));
                    questReceived = false;
                }
            }
        }
    }

}
