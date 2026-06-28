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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class ForestController extends EntityController {
    @FXML
    private ImageView exclamation, orcView;
    @FXML
    private Pane orcSpawn;
    @FXML
    private Circle forestSpirit;
    @FXML
    private Rectangle blackScreen,
            leftExit, rightExit, upExit, downExit,
            spawnLeftDownCorner, spawnLeftUpCorner, spawnRightUpCorner, spawnRightDownCorner;
    private Bounds rightExitHitbox, leftExitHitbox, upExitHitbox, downExitHitbox;
    private static boolean questReceived = false, questSolved = false;

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
        meetForestSpirit();
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
            Orc orc = generateOrc();
            orcView = new ImageView(imageOrc);
            orcView.setViewport(new Rectangle2D(0, 0 , 100, 100));
            Main.orcs.put(orc, orcView);

            orcView.setFitWidth(260);
            orcView.setFitHeight(260);
            orcView.setPreserveRatio(true);

            //orcView.setVisible(false);

            orcView.setLayoutX(setXOrc(spawnCorner));
            orcView.setLayoutY(setYOrc(spawnCorner));

            orcSpawn.getChildren().add(orcView);
        }
    }
    private double setYOrc(Rectangle spawnCorner){
        Random rand = new Random();
        double orcHeight = orcView.getBoundsInParent().getHeight();
        double minY = spawnCorner.getLayoutY();
        double maxY = minY + spawnCorner.getHeight() - orcHeight;
        return minY + (maxY - minY) * rand.nextDouble();
    }
    private double setXOrc(Rectangle spawnCorner){
        Random rand = new Random();
        double orcWidth = orcView.getBoundsInParent().getWidth();
        double minX = spawnCorner.getLayoutX();
        double maxX = minX + spawnCorner.getWidth() - orcWidth;
        return minX + (maxX - minX) * rand.nextDouble();
    }
    private Orc generateOrc(){
        Random rand = new Random();

        int randomLevel = rand.nextInt(10) + 1;
        int lvlOrc;
        if(randomLevel <= 6){
            lvlOrc = 1;
        }else if(randomLevel <= 9){
            lvlOrc = 2;
        }else{
            lvlOrc = 3;
        }
        Orc orc = new Orc(lvlOrc);

        loadImagesOrc(lvlOrc);

        return orc;
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
    private void meetForestSpirit(){
        Bounds hitboxPlayer = Main.player.getHitbox(playerView.getLayoutX() + 70, playerView.getLayoutY() + 55);
        Bounds hitboxForestSpirit = forestSpirit.getBoundsInParent();
        if(hitboxPlayer.intersects(hitboxForestSpirit)) {
            if(!questReceived){
                System.out.println(Main.guardian.getRandomQuest().toString());
                questReceived = true;
            }
        }
    }
    public static void questCompletedControl(){
        if(questReceived){
            if(Main.orcEncountered.getLevel() == Main.guardian.getQuestReceived().getLevel() && Main.guardian.getQuestReceived().getHowMuch() >= 1){
                Main.guardian.getQuestReceived().decreaseHowMuch();
                System.out.println("DECREMENTO" + Main.guardian.getQuestReceived().getHowMuch());
                if(Main.guardian.getQuestReceived().getHowMuch() < 1) {
                    System.out.println("Hai completato la quest");
                    Main.player.addPotion(Main.guardian.getPotionReward(Main.guardian.getQuestReceived().getPotionRewardLevel()));
                    if(Main.player.getInventory().getPotionAmount(Main.guardian.getQuestReceived().getPotionRewardLevel()) > 1){

                    }
                    questSolved = true;
                    questReceived = false;
                }
            }
        }
    }

}
