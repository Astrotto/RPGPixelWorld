package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;

import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class LoaderController {

    @FXML
    public Rectangle forest, home, graveyard,
                     healthBarPlayer, healthBarOrc,
                     experienceBarPlayer, experienceBarOrc,
                     level1Player, level2Player, level3Player,
                     level1Orc, level2Orc, level3Orc;
    @FXML
    public Image imageLeft, imageRight, imageOrc,
                 imageExclamation, imageHealthBar,
                 imageOrcAttack, imagePlayerAttack,
                 imageBtnAttack, imageBtnRun;
    @FXML
    public ImageView playerView, orcView,
                     healthBarViewPlayer, healthBarViewOrc,
                     experienceBarViewPlayer, experienceBarViewOrc,
                     backgroundView,
                     slotPotionLV1View, slotPotionLV2View, slotPotionLV3View,
                     potionLV1View, potionLV2View, potionLV3View,
                     btnAttack, btnRun;
    public Text amountLV1, amountLV2, amountLV3;
    public Pane gameWorld;
    public final int FRAME_WIDTH = 100;
    public final int FRAME_HEIGHT = 100;

    public void initialize() {
        player.setEntityView(playerView);
        playerHealthBar.setBar(healthBarViewPlayer ,healthBarPlayer);

        playerHealthBar.showHealthBar();

        showLevelPlayer();
        loadEntity();
        //loadExperienceBar(experienceBarPlayer, healthBarViewPlayer, player);
        loadInventory();
    }


    public void showLevelPlayer(){
        loadPlayerImages();
        switch (player.getExperience().getLevel()) {
            case 1 -> loadLevelPlayer(level1Player, 138, 122);
            case 2 -> {
                loadLevelPlayer(level1Player, 138, 122);
                loadLevelPlayer(level2Player, 154, 122);
            }
            case 3 -> {
                loadLevelPlayer(level1Player, 138, 122);
                loadLevelPlayer(level2Player, 154, 122);
                loadLevelPlayer(level3Player, 170, 122);
            }
        }
    }
    public void loadPlayerImages(){
        String playerName = switch (player.getExperience().getLevel()) {
            case 1 -> "knight";
            case 2 -> "templarKnight";
            case 3 -> "lancer";
            default -> "";
        };
        imageRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightImages/" + playerName + "WalkRight.png")));
        imageLeft = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightImages/" + playerName + "WalkLeft.png")));
        imagePlayerAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightImages/" + playerName + "Attack.png")));
    }
    public void loadLevelPlayer(Rectangle levelPlayer, int x, int y){
        levelPlayer.setVisible(true);
        levelPlayer.setLayoutX(playerHealthBar.getProgressBarView().getLayoutX() + x);
        levelPlayer.setLayoutY(playerHealthBar.getProgressBarView().getLayoutY() + y);
    }


    public void loadEntity(){
        player.getEntityView().setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        player.getEntityView().setSmooth(false);
    }



    private void loadInventory(){
        InventoryController.loadInventory();
        InventoryController.setSlotPotions(slotPotionLV1View);
        InventoryController.setSlotPotions(slotPotionLV2View);
        InventoryController.setSlotPotions(slotPotionLV3View);
        InventoryController.setPotionsView(potionLV1View, InventoryController.noPotionLV1);
        InventoryController.setPotionsView(potionLV2View, InventoryController.noPotionLV2);
        InventoryController.setPotionsView(potionLV3View, InventoryController.noPotionLV3);

    }

    public void setPotionObtained(){
        loadPotionObtained(potionLV1View, amountLV1, 1);
        loadPotionObtained(potionLV2View, amountLV2, 2);
        loadPotionObtained(potionLV3View, amountLV3, 3);

    }
    public void loadPotionObtained(ImageView potionView, Text amount, int level){
        if(player.getInventory().getPotionAmount(level) > 0){
            potionView.setImage(InventoryController.getPotionImages(level));
            loadAmount(amount, player.getInventory().getPotionAmount(level));
        }else{
            loadAmount(amount, 0);
        }
    }
    public void loadAmount(Text text, int amount){
        text.setText("" + amount);
    }


    public void loadButtonImages() {
        imageBtnAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnAttack.png")));
        imageBtnRun = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnRun.png")));
    }
    public void loadButtonDisabled() {
        imageBtnAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnAttackDisabled.png")));
        imageBtnRun = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/btnRunDisabled.png")));
    }
    public void loadButtons(){
        btnAttack.setImage(imageBtnAttack);
        btnAttack.setSmooth(false);
        btnRun.setImage(imageBtnRun);
        btnRun.setSmooth(false);
    }

    public void loadBackground(String nameMap){;
        backgroundView.setImage(loadBackgroundImage(nameMap));
        backgroundView.setSmooth(false);
    }

    public Image loadBackgroundImage(String backgroundName){
        return  new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/mapImages/" + backgroundName)));
    }





    public void loadOrcImages(Orc orc){
        String orcName = switch (orc.getExperience().getLevel()) {
            case 1 -> "armored";
            case 2 -> "elite";
            case 3 -> "rider";
            default -> "";
        };
        imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcImages/" + orcName + "Orc.png")));
        imageOrcAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcImages/" + orcName + "OrcAttack.png")));
    }
    public void setLevelOrc(){
        switch (orcEncountered.getExperience().getLevel()) {
            case 1 -> loadLevelOrc(level1Orc, 138, 122);
            case 2 -> {
                loadLevelOrc(level1Orc, 138, 122);
                loadLevelOrc(level2Orc, 154, 122);
            }
            case 3 -> {
                loadLevelOrc(level1Orc, 138, 122);
                loadLevelOrc(level2Orc, 154, 122);
                loadLevelOrc(level3Orc, 170, 122);
            }
        }
    }
    public void loadLevelOrc(Rectangle levelOrc, int x, int y){
        levelOrc.setVisible(true);
        levelOrc.setLayoutX(orcHealthBar.getProgressBarView().getLayoutX() + x);
        levelOrc.setLayoutY(orcHealthBar.getProgressBarView().getLayoutY() + y);
    }


    public void changeMap(Stage actualStage, String map){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/it/unicam/cs/mpgc/rpg129851/" + map + ".fxml")));
            Parent root = loader.load();
            Stage stage = actualStage;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            root.requestFocus();
        }catch(Exception e){
            System.err.println("Loading error of the " + map + " scene");
        }
    }
}
