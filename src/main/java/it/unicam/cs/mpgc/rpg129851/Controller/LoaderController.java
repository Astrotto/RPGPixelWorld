package it.unicam.cs.mpgc.rpg129851.Controller;

import it.unicam.cs.mpgc.rpg129851.Launch.Main;
import it.unicam.cs.mpgc.rpg129851.Model.Entity;
import it.unicam.cs.mpgc.rpg129851.Model.Orc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
                 imageOrcAttack, imagePlayerAttack
                ;
    @FXML
    public ImageView playerView, healthBarViewPlayer, backgroundView, healthBarViewOrc,
                     slotPotionLV1View, slotPotionLV2View, slotPotionLV3View,
                     potionLV1View, potionLV2View, potionLV3View;
    public Text amountLV1, amountLV2, amountLV3;
    public final int FRAME_WIDTH = 100;
    public final int FRAME_HEIGHT = 100;

    public void initialize() {
        loadLevelPlayer();
        loadEntity();
        loadHealthBarPlayer();
        loadExperienceBarPlayer();
        loadInventory();
    }

    public void loadEntity(){
        playerView.setViewport(new Rectangle2D(0,0,FRAME_WIDTH,FRAME_HEIGHT));
        playerView.setSmooth(false);
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

    public void loadHealthBarPlayer(){
        loadHealthBarImage();
        healthBarViewPlayer.setImage(imageHealthBar);
        healthBarPlayer.setWidth(Main.player.getHealthPercentage() * 130);

        changeColorHealthBar(Main.player, healthBarPlayer);
        healthBarPlayer.setLayoutX(healthBarViewPlayer.getLayoutX() + 50);
        healthBarPlayer.setLayoutY(healthBarViewPlayer.getLayoutY() + 113);

        healthBarViewPlayer.setSmooth(false);
    }
    public void loadExperienceBarPlayer(){
        experienceBarPlayer.setWidth(Main.player.getExperiencePercentage() * 85);
        experienceBarPlayer.setLayoutX(healthBarViewPlayer.getLayoutX() + 45);
        experienceBarPlayer.setLayoutY(healthBarViewPlayer.getLayoutY() + 122);
    }
    public void loadLevelPlayer(){
        if(Main.player.getLevel() == 1){
            loadLevel1Player();
            loadImagesKnightLV1();
        }else if(Main.player.getLevel() == 2){
            loadLevel2Player();
            loadImagesKnightLV2();
        }else{
            loadLevel3Player();
            loadImagesKnightLV3();
        }
    }
    public void loadLevel1Player(){
        level1Player.setVisible(true);
        level1Player.setLayoutX(healthBarViewPlayer.getLayoutX() + 138);
        level1Player.setLayoutY(healthBarViewPlayer.getLayoutY() + 122);
    }
    public void loadLevel2Player(){
        loadLevel1Player();
        level2Player.setVisible(true);
        level2Player.setLayoutX(healthBarViewPlayer.getLayoutX() + 154);
        level2Player.setLayoutY(healthBarViewPlayer.getLayoutY() + 122);
    }
    public void loadLevel3Player(){
        loadLevel2Player();
        level3Player.setVisible(true);
        level3Player.setLayoutX(healthBarViewPlayer.getLayoutX() + 170);
        level3Player.setLayoutY(healthBarViewPlayer.getLayoutY() + 122);
    }
    public void loadLevelOrc(){
        if(Main.orcEncountered.getLevel() == 1){
            loadLevel1Orc();
        }else if(Main.orcEncountered.getLevel() == 2){
            loadLevel2Orc();
        }else{
            loadLevel3Orc();
        }
    }
    public void loadLevel1Orc(){
        level1Orc.setVisible(true);
        level1Orc.setLayoutX(healthBarViewOrc.getLayoutX() + 138);
        level1Orc.setLayoutY(healthBarViewOrc.getLayoutY() + 122);
    }
    public void loadLevel2Orc(){
        loadLevel1Orc();
        level2Orc.setVisible(true);
        level2Orc.setLayoutX(healthBarViewOrc.getLayoutX() + 154);
        level2Orc.setLayoutY(healthBarViewOrc.getLayoutY() + 122);
    }
    public void loadLevel3Orc(){
        loadLevel2Orc();
        level3Orc.setVisible(true);
        level3Orc.setLayoutX(healthBarViewOrc.getLayoutX() + 170);
        level3Orc.setLayoutY(healthBarViewOrc.getLayoutY() + 122);
    }
    public void loadExperienceBarOrc(){
        experienceBarOrc.setWidth(Main.orcEncountered.getExperiencePercentage() * 85);
        experienceBarOrc.setLayoutX(healthBarViewOrc.getLayoutX() + 45);
        experienceBarOrc.setLayoutY(healthBarViewOrc.getLayoutY() + 122);
    }
    public void loadHealthBarOrc(Orc orc){
        loadHealthBarImage();
        healthBarViewOrc.setImage(imageHealthBar);
        healthBarOrc.setWidth(orc.getHealthPercentage() * 130);
        changeColorHealthBar(orc, healthBarOrc);
        healthBarOrc.setLayoutX(healthBarViewOrc.getLayoutX() + 50);
        healthBarOrc.setLayoutY(healthBarViewOrc.getLayoutY() + 113);
        healthBarViewOrc.setSmooth(false);
    }
    public void loadHealthBarImage(){
        imageHealthBar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/utilsImages/healthBarPlayer.png")));
    }
    public void changeColorHealthBar(Entity entity, Rectangle healthBar){
        if(entity.getHealthPercentage() > 0.60){
            healthBar.setFill(Color.GREEN);
        }else if(entity.getHealthPercentage() <= 0.60 && entity.getHealthPercentage() >= 0.30){
            healthBar.setFill(Color.ORANGE);
        }else {
            healthBar.setFill(Color.RED);
        }
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
        if(!Main.player.getInventory().isEmpty()){
            Main.player.getInventory().getPotions().forEach(potion -> {
                switch (potion.getLevel()){
                    case 1:
                        potionLV1View.setImage(InventoryController.potionLV1);
                        loadAmount(amountLV1, Main.player.getInventory().getPotionAmount(potion.getLevel()));
                        break;
                    case 2:
                        potionLV2View.setImage(InventoryController.potionLV2);
                        loadAmount(amountLV2, Main.player.getInventory().getPotionAmount(potion.getLevel()));
                        break;
                    case 3:
                        potionLV3View.setImage(InventoryController.potionLV3);
                        loadAmount(amountLV3, Main.player.getInventory().getPotionAmount(potion.getLevel()));
                }
            });
        }else{
            loadAmount(amountLV1, 0);
            loadAmount(amountLV2, 0);
            loadAmount(amountLV3, 0);
        }
    }

    public void loadAmount(Text text, int amount){
        //String nPotions = "X" + amount;
        text.setText("" + amount);
    }

    public void loadImagesKnightLV1() {
        imageRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV1Images/knightWalkRight.png")));
        imageLeft = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV1Images/knightWalkLeft.png")));
        imagePlayerAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV1Images/knightAttack.png")));

    }
    public void loadImagesKnightLV2() {
        imageRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV2Images/templarKnightWalkRight.png")));
        imageLeft = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV2Images/templarKnightWalkLeft.png")));
        imagePlayerAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV2Images/templarKnightAttack.png")));

    }
    public void loadImagesKnightLV3() {
        imageRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV3Images/lancerWalkRight.png")));
        imageLeft = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV3Images/lancerWalkLeft.png")));
        imagePlayerAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/knightLV3Images/lancerAttack.png")));

    }
    public void loadImagesOrc(int level){
        if(level == 1){
            imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV1Images/armoredOrc.png")));
        }else if(level == 2){
            imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV2Images/eliteOrc.png")));
        }else{
            imageOrc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV3Images/riderOrc.png")));
        }
    }

    public void loadOrcAttack(Orc orcEncountered){
        switch (orcEncountered.getLevel()){
            case 1:
                imageOrcAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV1Images/armoredOrcAttack.png")));
                break;
            case 2:
                imageOrcAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV2Images/eliteOrcAttack.png")));
                break;
            case 3:
                imageOrcAttack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/orcLV3Images/riderOrcAttack.png")));
        }
    }
    public void loadBackground(String nameMap){;
        backgroundView.setImage(loadBackgroundImage(nameMap));
        backgroundView.setSmooth(false);
    }

    public Image loadBackgroundImage(String backgroundName){
        return  new Image(Objects.requireNonNull(getClass().getResourceAsStream("/it/unicam/cs/mpgc/rpg129851/mapImages/" + backgroundName)));
    }

}
