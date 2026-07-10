package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector;
import it.unicam.cs.mpgc.rpg129851.PrintLog.PrintGameLog;
import it.unicam.cs.mpgc.rpg129851.View.ViewRegister;
import it.unicam.cs.mpgc.rpg129851.View.OrcView;
import it.unicam.cs.mpgc.rpg129851.View.PlayerView;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

import static it.unicam.cs.mpgc.rpg129851.Controller.OrcController.placeOrcRandomlyInACorner;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.ExclamationLoader.loadExclamation;
import static it.unicam.cs.mpgc.rpg129851.Launch.ChangerMap.changeMap;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.KeyDetector.*;
import static it.unicam.cs.mpgc.rpg129851.Movement.SpawnPoint.*;
import static it.unicam.cs.mpgc.rpg129851.Timeline.ChangeSceneTransition.startTransition;

public class ForestController extends EntityController {
    @FXML
    private AnchorPane forestPane;
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
        orcs.clear();
        ViewRegister.clearOrcs();
        placeOrcRandomly();
    }
    public void setKeyDetector() {
        forestPane.setOnKeyPressed(KeyDetector::manageKeyPressed);
        forestPane.setOnKeyReleased(KeyDetector::manageKeyReleased);
    }
    public void updatePlayerLocation() {
        super.updatePlayerLocation();
        exitCollision(leftExitHitbox, -50, 330);
        exitCollision(rightExitHitbox, 180, 340);
        exitCollision(upExitHitbox, 65, 185);
        exitCollision(downExitHitbox, 82, 430);
        orcCollisionDetection(orcs);
        meetForestSpirit();
    }
    public void exitCollision(Bounds exit, double x, double y){
        if(player.getHitbox(getNewX() + 70, getNewY() + 55).intersects(exit)) {
            timer.stop();
            startTransition(blackScreen, Duration.seconds(1.5),
                    ()-> {
                        changeMap("map");
                        setSpawnPoint(x, y);
                    }
            );
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
        PlayerView view = ViewRegister.ofPlayer(player);
        Bounds hitboxPlayer = player.getHitbox(view.getLayoutX() + 70, view.getLayoutY() + 55);
        orcList.forEach((orc) -> {
            OrcView orcBody = ViewRegister.ofOrc(orc);
            Bounds hitboxOrc = orc.getHitbox(orcBody.getLayoutX() + 70, orcBody.getLayoutY() + 55);
            if(hitboxPlayer.intersects(hitboxOrc)) {
                setOrcEncountered(orc);
                encounterEntity(orcBody.getView());
            }
        });
    }
    private void encounterEntity(ImageView orcView) {
        loadExclamation(exclamation);
        orcView.setVisible(true);
        keyPressed.clear();
        timer.stop();
        startTransition(blackScreen, Duration.seconds(1.5), this::startBattle);
    }
    private void startBattle() {
        exclamation.setVisible(false);
        changeMap("battle");
    }
    private void meetForestSpirit(){
        PlayerView view = ViewRegister.ofPlayer(player);
        Bounds hitboxPlayer = player.getHitbox(view.getLayoutX() + 70, view.getLayoutY() + 55);
        Bounds hitboxForestSpirit = forestSpirit.getBoundsInParent();
        if(hitboxPlayer.intersects(hitboxForestSpirit)) {
            if(!questReceived){
                PrintGameLog.info(guardian.getRandomQuest().toString());
                howMuch = guardian.getQuestReceived().getHowMuch();
                questReceived = true;
            }
        }
    }
    public static void questCompletedControl(){
        if(questReceived){
            if(orcEncountered.getExperience().getLevel() == guardian.getQuestReceived().getLevel() && guardian.getQuestReceived().getHowMuch() >= 1){
                howMuch--;
                if(howMuch < 1) {
                    PrintGameLog.info("Hai completato la quest");
                    player.getInventory().addPotion(guardian.getPotionReward(guardian.getQuestReceived().getPotionRewardLevel()));
                    questReceived = false;
                }
            }
        }
    }
}
