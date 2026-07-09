package it.unicam.cs.mpgc.rpg129851.Controller;

import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.BackgroundLoader.setBackgroundView;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PlayerLoader.*;
import static it.unicam.cs.mpgc.rpg129851.Launch.Main.*;
import static it.unicam.cs.mpgc.rpg129851.View.LevelView.*;
import static it.unicam.cs.mpgc.rpg129851.ImagesLoader.PotionLoader.*;


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
                     experienceBarPlayer, experienceBarOrc;
    @FXML
    public static Image imageOrc,
                 imageExclamation,
                 imageOrcAttack;
    @FXML
    public ImageView playerView, orcView,
                     progressBarViewPlayer, progressBarViewOrc,
                     experienceBarViewPlayer, experienceBarViewOrc,
                     backgroundView,
                     slotPotionLV1View, slotPotionLV2View, slotPotionLV3View,
                     potionLV1View, potionLV2View, potionLV3View,
                     btnAttack, btnRun;
    public Text amountLV1, amountLV2, amountLV3;
    public Pane gameWorld, levelPane;

    public void initialize() {
        player.getEntityView().setEntityView(playerView);
        playerHealthBar.setBar(progressBarViewPlayer, healthBarPlayer);
        playerExperienceBar.setBar(progressBarViewPlayer, experienceBarPlayer);

        playerHealthBar.showHealthBar();
        playerExperienceBar.showExperienceBar();
        loadPlayerImages();
        showLevel(player, playerHealthBar, levelPane);
        loadPlayerView();
        //loadExperienceBar(experienceBarPlayer, healthBarViewPlayer, player);
        loadInventory();
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



    public void changeMap(Stage actualStage, String map){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/it/unicam/cs/mpgc/rpg129851/" + map + "-view.fxml")));
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
